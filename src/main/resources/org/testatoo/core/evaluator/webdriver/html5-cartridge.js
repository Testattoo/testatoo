///*
// * Copyright (C) 2013 Ovea (dev@ovea.com)
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *         http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
//(function(w) {
//
//  var cartridge = {
//    name: 'html5',
//    components: [],
//    support: {}
//  };
//
//  var $ = w.testatoo;
//
//  $.registerCartridge(cartridge);
//
//  cartridge.support.base = {
//    enabled: function(el) {
//      return !this.disabled(el);
//    },
//    disabled: function(el) {
//      return el.is(':disabled') || el.attr('disabled') != undefined;
//    },
//    hidden: function(el) {
//      return el.is(':hidden');
//    },
//    visible: function(el) {
//      return !this.hidden(el);
//    }
//  };
//
//  cartridge.support.input = {
//    placeholder: function(el) {
//      return el.prop('placeholder');
//    },
//    label: function(el) {
//      var label = $('label[for="' + el.attr('id') + '"]');
//      if (label.length > 0) return label.text();
//
//      var p = el.prev('label');
//      if (p.length > 0) return p.text();
//      return el.parent().text().trim();
//    },
//    optional: function(el) {
//      return !this.required(el);
//    },
//    required: function(el) {
//      return el.prop('required');
//    },
//    value: function(el) {
//      return el.val();
//    },
//    empty: function(el) {
//      return $.trim(el.val()).length == 0;
//    },
//    valid: function(el) {
//      return el.is(':valid');
//    },
//    invalid: function(el) {
//      return el.is(':invalid');
//    }
//  };
//
//  cartridge.support.container = {
//    contains: function(el, ids) {
//      var not = [];
//      $.each(ids, function(index, _id) {
//        !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
//      });
//      return not;
//    },
//    display: function(el, ids) {
//      return this.contains(el, ids);
//    }
//  };
//
//  cartridge.support.size = {
//    size: function(el) {
//      return el.children().length;
//    }
//  };
//
//  cartridge.support.checkable = {
//    checked: function(el) {
//      return el.is(':checked');
//    },
//    unchecked: function(el) {
//      return !this.checked(el);
//    }
//  };
//
//  cartridge.support.selectable = {
//    selected: function(el) {
//      return el.prop('selected');
//    },
//    unselected: function(el) {
//      return !this.selected(el);
//    },
//    select: function(el) {
//      el.prop('selected', true).trigger('change');
//    },
//    unselect: function(el) {
//      el.prop('selected', false).trigger('change');
//    }
//  };
//
//  var support = cartridge.support;
//
//  cartridge.components.push($.support([support.base, support.container], {
//    type: 'Panel',
//    match: function(el) { return el.is('div'); },
//    title: function() {
//      return '';
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Button',
//    match: function(el) {
//      var type = ['button', 'submit', 'reset', 'image'];
//      return el.is('button') || (el.is('input') && type.indexOf(el.attr('type')) !== -1);
//    },
//    text: function(el) {
//      if (el.is('input'))
//        return el.val();
//      return el.text();
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input, support.checkable], {
//    type: 'Checkbox',
//    match: function(el) { return el.is('input') && el.attr('type') === 'checkbox'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input, support.checkable], {
//    type: 'Radio',
//    match: function(el) { return el.is('input') && el.attr('type').toLowerCase() === 'radio'; }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'DataGrid',
//    match: function(el) { return el.is('table'); },
//    columnsize: function(el) {
//      return el.find('thead tr:last th').length;
//    },
//    size: function(el) {
//      return el.find('tbody tr').length;
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Column',
//    match: function(el) { return el.is('th'); },
//    title: function(el) {
//      return el.text().trim();
//    },
//    size: function(el) {
//      var index = el.index() + 1;
//      return el.closest('table').find('tbody tr').find('td:nth-child(' + index + ')').length;
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Row',
//    match: function(el) { return el.is('tr'); },
//    title: function(el) {
//      return el.find('th:first').text().trim();
//    },
//    size: function(el) {
//      return el.find('td').length;
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Cell',
//    match: function(el) { return el.is('td'); },
//    value: function(el) {
//      return el.text().trim();
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.selectable], {
//    type: 'Item',
//    match: function(el) { return el.is('option') || el.is('li'); },
//    label: function(el) {
//      return el.attr('label');
//    },
//    value: function(el) {
//      return el.text().trim();
//    },
//    disabled: function(el) {
//      return el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Link',
//    match: function(el) { return el.is('a'); },
//    text: function(el) {
//      return el.text().trim();
//    },
//    reference: function(el) {
//      return el.prop('href');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Image',
//    match: function(el) { return el.is('img'); },
//    source: function(el) {
//      return el.prop('src');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.container], {
//    type: 'Form',
//    match: function(el) { return el.is('form'); },
//    valid: function(el) {
//      return el.is(':valid');
//    },
//    invalid: function(el) {
//      return el.is(':invalid');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'Dropdown',
//    match: function(el) { return el.is('select') && !el.attr('multiple') && !el.prop('size') > 0; },
//    size: function(el) {
//      return el.find('option').length;
//    },
//    groupitemssize: function(el) {
//      return el.find('optgroup').length;
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'GroupItem',
//    match: function(el) { return el.is('optgroup'); },
//    label: function(el) {
//      return el.attr('label');
//    },
//    size: function(el) {
//      return el.find('option').length;
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input, support.size], {
//    type: 'ListBox',
//    match: function(el) { return el.is('select') && (el.attr('multiple') || el.prop('size') > 0); },
//    multiselectable: function(el) {
//      return el.is('select') && el.prop('multiple');
//    },
//    singleselectable: function(el) {
//      return !this.multiselectable(el);
//    },
//    disabled: function(el) {
//      return (el.is('option') || el.is('optgroup')) && el.closest('select').is(':disabled');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.size], {
//    type: 'ListView',
//    match: function(el) { return el.is('ul') || el.is('ol'); },
//    empty: function(el) {
//      return el.find('li').length == 0;
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'TextField',
//    match: function(el) { return el.is('textarea') || (el.is('input') && el.attr('type') === 'text'); }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'EmailField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'email'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'PasswordField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'password'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'PhoneField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'tel'; },
//    pattern: function(el) {
//      return el.prop('pattern');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'URLField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'url'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'SearchField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'search'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'NumberField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'number'; },
//    maximum: function(el) {
//      return el.prop('max');
//    },
//    minimum: function(el) {
//      return el.prop('min');
//    },
//    step: function(el) {
//      return el.prop('step');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'RangeField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'range'; },
//    maximum: function(el) {
//      return el.prop('max');
//    },
//    minimum: function(el) {
//      return el.prop('min');
//    },
//    step: function(el) {
//      return el.prop('step');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'ColorField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'color'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'FileDialog',
//    match: function(el) { return el.is('input') && el.attr('type') === 'file'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'MonthField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'month'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'WeekField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'week'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'DateField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'date'; },
//    maximum: function(el) {
//      return el.prop('max');
//    },
//    minimum: function(el) {
//      return el.prop('min');
//    }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'TimeField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'time'; }
//  }));
//
//  cartridge.components.push($.support([support.base, support.input], {
//    type: 'DateTimeField',
//    match: function(el) { return el.is('input') && el.attr('type') === 'datetime'; }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Article',
//    match: function(el) { return el.is('article'); }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Aside',
//    match: function(el) { return el.is('aside'); }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Footer',
//    match: function(el) { return el.is('footer'); }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Header',
//    match: function(el) { return el.is('header'); }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Section',
//    match: function(el) { return el.is('section'); }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Paragraph',
//    match: function(el) { return el.is('p'); },
//    text: function(el) {
//      return el.text().trim();
//    }
//  }));
//
//  cartridge.components.push($.support([support.base], {
//    type: 'Heading',
//    match: function(el) { return el.is('h1') || el.is('h2') || el.is('h3') || el.is('h4') || el.is('h5') || el.is('h6'); },
//    text: function(el) {
//      return el.text().trim();
//    }
//  }));
//
//}(window));