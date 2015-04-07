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
(function (w) {

  var cartridge = {
    name: 'my_custom_cartridge',
    components: []
  };

  w.testatoo.registerCartridge(cartridge);

  cartridge.components.push({
    type: 'MyCustomComponent',
    match: function(el) {
      return el.attr('data-role') == 'my-custom-comp'
    }
  });

  cartridge.components.push({
    type: 'CustomField',
    match: function(el) {
      return el.attr('data-role') == 'custom-field'
    }
  });


//  w.testatoo.registerCartridge(
//    {
//      name: 'my_custom_cartridge',
//      type: function (el) {
//        if (el.attr('data-role') == 'my-custom-comp')
//          return 'MyCustomComponent';
//        if (el.attr('data-role') == 'custom-field')
//          return 'CustomField';
//        return undefined;
//      },
//      states: {
//        enabled: function () {
//          return false;
//        },
//        visible: function () {
//          return undefined;
//        },
//        valid: function(id) {
//          return $('#' + id).hasClass('valid');
//        },
//        invalid: function(id) {
//          return $('#' + id).hasClass('invalid');
//        }
//      },
//      properties: {
//        label: function (id) {
//          if ($('#' + id).attr('data-role') == 'custom-field')
//            return 'Label overridden';
//        },
//        placeholder: function() {
//          return undefined;
//        }
//      },
//      functions: {},
//      extensions: {}
//    }
//  );

}(window));
