/*
 * Copyright (C) 2013 Ovea (dev@ovea.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *         http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
(function(w) {

  // See http://api.jquery.com/jQuery.noConflict/ and Stack Overflow
  w.testatoo = w.jQuery.noConflict(true);
  var jQuery = w.testatoo,
    $ = w.testatoo,
    webkit = !!window.webkitRequestAnimationFrame;

  var cartridges = [];

  $.registerCartridge = function(cartridge) {
    cartridges.push(cartridge);
  };

  function getInfo(el) {
    var info = { cartridge: 'html5', type:  el.prop('tagName') };

    cartridges.forEach(function(cartridge) {
      cartridge.components.forEach(function(component) {
        if (component.match(el)) {
          info.cartridge = cartridge.name;
          info.type = component.type;
        }
      });
    });
    return info;
  }

  $.fn.getMetaInfos = function() {
    var metaInfos = [];
    this.each(function() {
      var me = $(this),
        id = $(this).attr('id');
      if (!id) {
        id = 'gen-' + Math.round(new Date().getTime() * Math.random());
        me.attr('id', id);
      }
      var info = getInfo(me);

      metaInfos.push({
        id: id,
        node: me.prop('nodeName').toLowerCase(),
        type: info.type,
        cartridge: info.cartridge
      });
    });
    return metaInfos;
  };

  $.evaluate = function(id, cartridge, type, method, params) {
    var evaluation;
    cartridges.forEach(function(_cartridge) {
      if (_cartridge.name === cartridge) {
        _cartridge.components.forEach(function(component) {
          if (component.type === type) {
            try {
              evaluation = component[method]($('#' + id), params);
            } catch (e) {
              throw new Error('Unable to find method "' + method + '" on component type "' + type + '"');
            }
          }
        });
      }
    });
    return evaluation;
  };

}(window));
