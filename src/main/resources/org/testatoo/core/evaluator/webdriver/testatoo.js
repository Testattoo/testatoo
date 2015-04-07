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
    cartridges[cartridge.name] = cartridge.components;
  };

  function getInfo(el) {
    var info = { cartridge: 'html5', type: el.prop("tagName")};
    Object.keys(cartridges).forEach(function(key) {
      cartridges[key].forEach(function(component) {
        if (component.match(el)) {
          info.cartridge = key;
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

  $.evaluate = function(id, cartridge, type, method) {
    var evaluation;
      cartridges[cartridge].forEach(function(component) {
        if (component.type === type) {
          try {
            evaluation = component[method]($('#' + id));
          } catch (e) {
            throw new Error('Unable to find method "' + method + '" on component type "' + type + '"');
          }
        }
      });
    return evaluation;
  };


  //var html5_cartridge = {
  //  name: 'html5',
  //
  //  type: function (el) {
  //    if (el.is('textarea')) return 'TextField';
  //    if (el.is('img')) return 'Image';
  //    if (el.is('h1') || el.is('h2') || el.is('h3') || el.is('h4') || el.is('h5') || el.is('h6')) return 'Heading';
  //    if (el.is('div')) return 'Panel';
  //    if (el.is('ul')) return 'ListView';
  //    if (el.is('ol')) return 'ListView';
  //    if (el.is('form')) return 'Form';
  //    if (el.is('p')) return 'Paragraph';
  //    if (el.is('input')) {
  //      switch (el.attr('type').toLowerCase() || '') {
  //        case 'radio':
  //          return 'Radio';
  //        case 'text':
  //          return 'TextField';
  //        case 'password':
  //          return 'PasswordField';
  //        case 'email':
  //          return 'EmailField';
  //        case 'tel':
  //          return 'PhoneField';
  //        case 'url':
  //          return 'URLField';
  //        case 'search':
  //          return 'SearchField';
  //        case 'number':
  //          return 'NumberField';
  //        case 'range':
  //          return 'RangeField';
  //        case 'color':
  //          return 'ColorField';
  //        case 'file':
  //          return 'FileDialog';
  //        case 'month':
  //          return 'MonthField';
  //        case 'week':
  //          return 'WeekField';
  //        case 'date':
  //          return 'DateField';
  //        case 'time':
  //          return 'TimeField';
  //        case 'datetime':
  //          return 'DateTimeField';
  //        default:
  //          return undefined;
  //      }
  //    }
  //    return el.prop("tagName");
  //  },
  //  states: {
  //    valid: function(id) {
  //      return $('#' + id).is(':valid');
  //    },
  //    invalid: function(id) {
  //      return $('#' + id).is(':invalid');
  //    },
  //    enabled: function (id) {
  //      return !this.disabled(id);
  //    },
  //    disabled: function (id) {
  //      var el = $('#' + id);
  //      return el.is(':disabled') || el.attr('disabled') != undefined || (el.is('option') || el.is('optgroup')) && el.closest('select').is(':disabled');
  //    },
  //    hidden: function (id) {
  //      return $('#' + id).is(':hidden');
  //    },
  //    visible: function (id) {
  //      return !this.hidden(id);
  //    }
  //    required: function (id) {
  //      return $('#' + id).prop('required');
  //    },
  //    optional: function (id) {
  //      return !this.required(id);
  //    },
  //    selected: function (id) {
  //      return $('#' + id).prop('selected');
  //    },
  //    unselected: function (id) {
  //      return !this.selected(id);
  //    },
  //    empty: function (id) {
  //      var el = $('#' + id + '');
  //      var nodeName = el.prop('nodeName').toLowerCase() || '';
  //      switch (nodeName) {
  //        case 'input':
  //          return $.trim(el.val()).length == 0;
  //          break;
  //        case 'ol':
  //        case 'ul':
  //          return el.find('li').length == 0;
  //          break;
  //        default:
  //          return false;
  //      }
  //    }
  //  },
  //  properties: {
  //    maximum: function (id) {
  //      return $('#' + id).prop('max');
  //    },
  //    minimum: function (id) {
  //      return $('#' + id).prop('min');
  //    },
  //    placeholder: function (id) {
  //      return $('#' + id).prop('placeholder');
  //    },
  //    pattern: function (id) {
  //      return $('#' + id).prop('pattern');
  //    },
  //    step: function (id) {
  //      return $('#' + id).prop('step');
  //    },
  //
  //    title: function (id) {
  //      var el = $('#' + id);
  //      if (el.is('th'))
  //        return el.text();
  //      return el.prop('title');
  //    },
  //    value: function (id) {
  //      var el = $('#' + id);
  //      if (el.is('input') || el.is('textarea'))
  //        return el.val();
  //      return el.text();
  //    },
  //    size: function (id) {
  //      var el = $('#' + id);
  //      if (el.is('ul') || el.is('ol'))
  //        return el.find('li').length;
  //      }
  //
  //      return el.children().length;
  //    },
  //  },
  //  actions: {
  //    select: function (id) {
  //      $('#' + id).prop('selected', true).trigger('change');
  //    },
  //    unselect: function (id) {
  //      $('#' + id).prop('selected', false).trigger('change');
  //    }
  //  },
  //  extensions: {
  //    contains: function (id, ids) {
  //      var el = $('#' + id + '');
  //      var not = [];
  //      $.each(ids, function (index, _id) {
  //        !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
  //      });
  //      return not;
  //    },
  //    display: function (id, ids) {
  //      var el = $('#' + id + '');
  //      var not = [];
  //      $.each(ids, function (index, _id) {
  //        !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
  //      });
  //      return not;
  //    }
  //  }
  //};

  //$.registerCartridge(html5_cartridge);


  //function getCartridge(name) {
  //  var resultList = $.grep(cartridges, function (element) {
  //    return element.name === name;
  //  });
  //
  //  if (resultList.length)
  //    return resultList[0];
  //  return html5_cartridge;
  //}


  //$.property = function (cartridge, property, id, type) {
  //  return evaluate('properties', cartridge, property, id, type);
  //};
  //
  //$.state = function (cartridge, state, id, type) {
  //  return evaluate('states', cartridge, state, id, type);
  //};
  //
  //$.action = function (cartridge, action, id, type) {
  //  return evaluate('actions', cartridge, action, id, type);
  //};
  //
  //$.extension = function() {
  //  var used_cartridge = getCartridge(arguments[0]);
  //  var extension_name = arguments[1];
  //  [].shift.apply(arguments);
  //  [].shift.apply(arguments);
  //  if (used_cartridge.extensions[extension_name])
  //     return used_cartridge.extensions[extension_name].apply(this, arguments);
  //  return html5_cartridge.extensions[extension_name].apply(this, arguments);
  //};

  //function evaluate(scope, cartridge, target, id, type) {
  //  var used_cartridge = getCartridge(cartridge);
  //  if (used_cartridge[scope][target] && used_cartridge[scope][target](id, type) != undefined)
  //    return used_cartridge[scope][target](id, type);
  //  return html5_cartridge[scope][target](id, type);
  //}

}(window));
