/*
 * Copyright © 2018 Ovea (d.avenante@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
(function ($) {
  $.fn.testatoo = function (options) {
    switch (options.method) {
      case 'metaInfos':
        var metaInfos = [];
        this.each(function () {
          var me = $(this),
            id = $(this).attr('id');
          if (!id) {
            id = 'gen-' + Math.round(new Date().getTime() * Math.random());
            me.attr('id', id);
          }

          metaInfos.push({
            // See http://learn.jquery.com/using-jquery-core/faq/how-do-i-select-an-element-by-an-id-that-has-characters-used-in-css-notation/
            id: id.replace(/(:|\.|\[|\]|,|=|@)/g, "\\$1"),
            node: me.prop('nodeName').toLowerCase()
          });
        });
        return metaInfos;
      case 'contains':
        var el = $('#' + options.id);
        var not = [];
        $.each(options.ids, function (index, _id) {
          !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
        });
        return not;
    }
  };
}(window.jQuery));
