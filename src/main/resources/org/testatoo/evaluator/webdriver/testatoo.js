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
    $ = w.testatoo;

  $.fn.metaInfos = function() {
    var metaInfos = [];
    this.each(function() {
      var me = $(this),
        id = $(this).attr('id');
      if (!id) {
        id = 'gen-' + Math.round(new Date().getTime() * Math.random());
        me.attr('id', id);
      }

      metaInfos.push({
        id: id,
        node: me.prop('nodeName').toLowerCase()
      });
    });
    return metaInfos;
  };

  $._contains = function(id, ids) {
      var el = $('#' + id);
      var not = [];
      $.each(ids, function(index, _id) {
        !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
      });
      return not;
  };

}(window));
