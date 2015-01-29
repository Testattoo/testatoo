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

  // See http://api.jquery.com/jQuery.noConflict/ and Stack Overflow
  w.testatoo = w.jQuery.noConflict(true);
  var jQuery = w.testatoo,
    $ = w.testatoo,
    webkit = !!window.webkitRequestAnimationFrame;

  var cartridges = [];

  $.registerCartridge = function (cartridge) {
    cartridges.unshift(cartridge);
  };

  var html5_cartridge = {
    name: 'html5',
    type: function (el) {
      if (el.is('button')) return 'Button';
      if (el.is('textarea')) return 'TextField';
      if (el.is('img')) return 'Image';
      if (el.is('a')) return 'Link';
      if (el.is('h1') || el.is('h2') || el.is('h3') || el.is('h4') || el.is('h5') || el.is('h6')) return 'Heading';
      if (el.is('div')) return 'Panel';
      if (el.is('ul')) return 'ListView';
      if (el.is('ol')) return 'ListView';
      if (el.is('select')) return (el.attr('multiple') || el.prop('size') > 0) ? 'ListBox' : 'Dropdown';
      if (el.is('option') || el.is('li')) return 'Item';
      if (el.is('optgroup')) return 'GroupItem';
      if (el.is('form')) return 'Form';
      if (el.is('table')) return 'DataGrid';
      if (el.is('tr')) return 'Row';
      if (el.is('td')) return 'Cell';
      if (el.is('th')) return 'Column';
      if (el.is('article')) return 'Article';
      if (el.is('aside')) return 'Aside';
      if (el.is('footer')) return 'Footer';
      if (el.is('header')) return 'Header';
      if (el.is('section')) return 'Section';
      if (el.is('input')) {
        switch (el.attr('type').toLowerCase() || '') {
          case 'radio':
            return 'Radio';
          case 'checkbox':
            return 'Checkbox';
          case 'text':
            return 'TextField';
          case 'password':
            return 'PasswordField';
          case 'email':
            return 'EmailField';
          case 'tel':
            return 'PhoneField';
          case 'url':
            return 'URLField';
          case 'search':
            return 'SearchField';
          case 'number':
            return 'NumberField';
          case 'range':
            return 'RangeField';
          case 'color':
            return 'ColorField';
          case 'file':
            return 'FileDialog';
          case 'month':
            return 'MonthField';
          case 'week':
            return 'WeekField';
          case 'date':
            return 'DateField';
          case 'time':
            return 'TimeField';
          case 'datetime':
            return 'DateTimeField';
          case 'button':
            return 'Button';
          case 'submit':
            return 'Button';
          case 'reset':
            return 'Button';
          case 'image':
            return 'Button';
          default:
            return undefined;
        }
      }
      return el.prop("tagName");
    },
    states: {
      valid: function(id) {
        return $('#' + id).is(':valid');
      },
      invalid: function(id) {
        return $('#' + id).is(':invalid');
      },
      enabled: function (id) {
        return !this.disabled(id);
      },
      disabled: function (id) {
        var el = $('#' + id);
        return el.is(':disabled') || el.attr('disabled') != undefined || (el.is('option') || el.is('optgroup')) && el.closest('select').is(':disabled');
      },
      hidden: function (id) {
        return $('#' + id).is(':hidden');
      },
      visible: function (id) {
        return !this.hidden(id);
      },
      checked: function (id) {
        return $('#' + id).is(':checked');
      },
      unchecked: function (id) {
        return !this.checked(id);
      },
      required: function (id) {
        return $('#' + id).prop('required');
      },
      optional: function (id) {
        return !this.required(id);
      },
      selected: function (id) {
        return $('#' + id).prop('selected');
      },
      unselected: function (id) {
        return !this.selected(id);
      },
      multiselectable: function (id) {
        var el = $('#' + id);
        return el.is('select') && el.prop('multiple');
      },
      singleselectable: function (id) {
        return !this.multiselectable(id);
      },
      empty: function (id) {
        var el = $('#' + id + '');
        var nodeName = el.prop('nodeName').toLowerCase() || '';
        switch (nodeName) {
          case 'input':
            return $.trim(el.val()).length == 0;
            break;
          case 'ol':
          case 'ul':
            return el.find('li').length == 0;
            break;
          default:
            return false;
        }
      }
    },
    properties: {
      label: function (id) {
        var el = $('#' + id);
        if (el.is('option') || el.is('optgroup')) {
          return el.attr('label');
        }

        var label = $('label[for="' + id + '"]');
        if (label.length > 0) return label.text();

        var p = el.prev('label');
        if (p.length > 0) return p.text();
        return el.parent().text().trim();
      },
      maximum: function (id) {
        return $('#' + id).prop('max');
      },
      minimum: function (id) {
        return $('#' + id).prop('min');
      },
      placeholder: function (id) {
        return $('#' + id).prop('placeholder');
      },
      pattern: function (id) {
        return $('#' + id).prop('pattern');
      },
      reference: function (id) {
        return $('#' + id).prop('href');
      },
      source: function (id) {
        return $('#' + id).prop('src');
      },
      step: function (id) {
        return $('#' + id).prop('step');
      },
      text: function (id) {
        var el = $('#' + id);
        if (el.is('input'))
          return el.val();
        return el.text();
      },
      title: function (id) {
        var el = $('#' + id);
        if (el.is('th'))
          return el.text();
        return el.prop('title');
      },
      value: function (id) {
        var el = $('#' + id);
        if (el.is('input'))
          return el.val();
        return el.text();
      },
      size: function (id) {
        var el = $('#' + id);
        if (el.is('ul') || el.is('ol'))
          return el.find('li').length;
        if (el.is('table'))
          return el.find('tbody tr').length;
        if (el.is('select') || el.is('optgroup'))
          return el.find('option').length;
        if (el.is('tr'))
          return el.find('td').length;
        if (el.is('th')) {
          var index = el.index() + 1;
          return el.closest('table').find('tbody tr').find('td:nth-child(' + index + ')').length;
        }

        return el.children().length;
      },
      columnsize: function (id) {
        var el = $('#' + id);
        if (el.is('table'))
          return el.find('thead tr:last th').length;
        return undefined;
      },
      groupitemssize: function (id) {
        return $('#' + id).find('optgroup').length;
      },
      visibleitemssize: function (id) {
        return $('#' + id).prop('size');
      }
    },
    actions: {
      select: function (id) {
        $('#' + id).prop('selected', true).trigger('change');
      },
      unselect: function (id) {
        $('#' + id).prop('selected', false).trigger('change');
      }
    },
    extensions: {
      contains: function (id, ids) {
        var el = $('#' + id + '');
        var not = [];
        $.each(ids, function (index, _id) {
          !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
        });
        return not;
      },
      display: function (id, ids) {
        var el = $('#' + id + '');
        var not = [];
        $.each(ids, function (index, _id) {
          !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
        });
        return not;
      }
    }
  };

  $.registerCartridge(html5_cartridge);

  function getInfo(el) {
    var _type = undefined;
    var _cartridge = undefined;

    cartridges.forEach(function (cartridge) {
      if (!_type) {
        _type = cartridge.type(el);
        _cartridge = cartridge.name;
      }
    }, this);
    return {type: _type, cartridge: _cartridge};
  }

  function getCartridge(name) {
    var resultList = $.grep(cartridges, function (element) {
      return element.name === name;
    });

    if (resultList.length)
      return resultList[0];
    return html5_cartridge;
  }

  $.fn.getMetaInfos = function () {
    var metaInfos = [];
    this.each(function () {
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
        type: info ? info.type : info,
        cartridge: info ? info.cartridge : info
      });
    });
    return metaInfos;
  };

  $.property = function (cartridge, property, id) {
    var used_cartridge = getCartridge(cartridge);
    if (used_cartridge.properties[property] && used_cartridge.properties[property](id))
      return used_cartridge.properties[property](id);
    return html5_cartridge.properties[property](id);
  };

  $.state = function (cartridge, state, id) {
    var used_cartridge = getCartridge(cartridge);
    if (used_cartridge.states[state] && used_cartridge.states[state](id))
      return used_cartridge.states[state](id);
    return html5_cartridge.states[state](id);
  };

  $.action = function (cartridge, action, id) {
    var used_cartridge = getCartridge(cartridge);
    if (used_cartridge.actions[action] && used_cartridge.actions[action](id))
      return used_cartridge.actions[action](id);
    return html5_cartridge.actions[action](id);
  };

  $.extension = function() {
    var used_cartridge = getCartridge(arguments[0]);
    var extension_name = arguments[1];
    [].shift.apply(arguments);
    [].shift.apply(arguments);
    if (used_cartridge.extensions[extension_name])
       return used_cartridge.extensions[extension_name].apply(this, arguments);
    return html5_cartridge.extensions[extension_name].apply(this, arguments);
  }

}(window));
