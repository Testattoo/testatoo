// HTML5 Cartridge
(function(w) {

  var cartridge = {
    name: 'html5',
    components: []
  };

  var $ = w.testatoo;

  $.registerCartridge(cartridge);

  var base = {
    hidden: function(el) {
      return el.is(':hidden');
    },
    visible: function(id) {
      return !this.hidden(id);
    },
    enabled: function(el) {
      return !this.disabled(el);
    },
    disabled: function(el) {
      return el.is(':disabled') || el.attr('disabled') != undefined;
    },
    size: function(el) {
      return el.children().length;
    },
    contains: function(el, ids) {
      var not = [];
      $.each(ids, function(index, _id) {
        !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
      });
      return not;
    },
    display: function(el, ids) {
      return this.contains(el, ids);
    }
  };

  var valid = {
    valid: function(el) {
      return el.is(':valid');
    },
    invalid: function(el) {
      return el.is(':invalid');
    }
  };

  var label = {
    label: function(el) {
      var label = $('label[for="' + el.attr('id') + '"]');
      if (label.length > 0) return label.text();

      var p = el.prev('label');
      if (p.length > 0) return p.text();
      return el.parent().text().trim();
    }
  };

  var field = $.extend({}, base, label, valid, {
    placeholder: function(el) {
      return el.prop('placeholder');
    },
    optional: function(el) {
      return !this.required(el);
    },
    required: function(el) {
      return el.prop('required');
    },
    value: function(el) {
      return el.val();
    },
    empty: function(el) {
      return $.trim(el.val()).length == 0;
    },
    maximum: function(el) {
      return el.prop('max');
    },
    minimum: function(el) {
      return el.prop('min');
    },
    pattern: function(el) {
      return el.prop('pattern');
    },
    step: function(el) {
      return el.prop('step');
    }
  });

  var checkable = $.extend({}, field, {
    checked: function(el) {
      return el.is(':checked');
    },
    unchecked: function(el) {
      return !this.checked(el);
    }
  });

  cartridge.components.push($.extend({}, base, {
    type: 'Panel',
    match: function(el) { return el.is('div'); },
    title: function() {
      return '';
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Button',
    match: function(el) {
      var type = ['button', 'submit', 'reset', 'image'];
      return el.is('button') || (el.is('input') && type.indexOf(el.attr('type')) !== -1);
    },
    text: function(el) {
      if (el.is('input'))
        return el.val();
      return el.text();
    }
  }));

  cartridge.components.push($.extend({}, checkable, {
    type: 'Checkbox',
    match: function(el) { return el.is('input') && el.attr('type') === 'checkbox'; }
  }));

  cartridge.components.push($.extend({}, checkable, {
    type: 'Radio',
    match: function(el) { return el.is('input') && el.attr('type').toLowerCase() === 'radio'; }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'DataGrid',
    match: function(el) { return el.is('table'); },
    columnsize: function(el) {
      return el.find('thead tr:last th').length;
    },
    size: function(el) {
      return el.find('tbody tr').length;
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Column',
    match: function(el) { return el.is('th'); },
    title: function(el) {
      return el.text().trim();
    },
    size: function(el) {
      return el.closest('table').find('tbody tr').find('td:nth-child(' + el.index() + 1 + ')').length;
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Row',
    match: function(el) { return el.is('tr'); },
    size: function(el) {
      return el.find('td').length;
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Cell',
    match: function(el) { return el.is('td'); },
    value: function(el) {
      return el.text().trim();
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Item',
    match: function(el) { return el.is('option') || el.is('li'); },
    label: function(el) {
      return el.attr('label');
    },
    value: function(el) {
      return el.text().trim();
    },
    selected: function(el) {
      return el.prop('selected');
    },
    unselected: function(el) {
      return !this.selected(el);
    },
    disabled: function(el) {
      return el.is(':disabled') || el.attr('disabled') != undefined || el.closest('select').is(':disabled');
    },
    select: function(el) {
      el.prop('selected', true).trigger('change');
    },
    unselect: function(el) {
      el.prop('selected', false).trigger('change');
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Link',
    match: function(el) { return el.is('a'); },
    text: function(el) {
      return el.text().trim();
    },
    reference: function(el) {
      return el.prop('href');
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Image',
    match: function(el) { return el.is('img'); },
    source: function(el) {
      return el.prop('src');
    }
  }));

  cartridge.components.push($.extend({}, base, valid, {
    type: 'Form',
    match: function(el) { return el.is('form'); }
  }));

  cartridge.components.push($.extend({}, base, label, {
    type: 'Dropdown',
    match: function(el) { return el.is('select') && !el.attr('multiple') && !el.prop('size') > 0; },
    size: function(el) {
      return el.find('option').length;
    },
    groupitemssize: function(el) {
      return el.find('optgroup').length;
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'GroupItem',
    match: function(el) { return el.is('optgroup'); },
    label: function(el) {
      return el.attr('label');
    },
    size: function(el) {
      return el.find('option').length;
    }
  }));

  cartridge.components.push($.extend({}, base, label, {
    type: 'ListBox',
    match: function(el) { return el.is('select') && (el.attr('multiple') || el.prop('size') > 0); },
    multiselectable: function(el) {
      return el.is('select') && el.prop('multiple');
    },
    singleselectable: function(el) {
      return !this.multiselectable(el);
    },
    disabled: function(el) {
      return (el.is('option') || el.is('optgroup')) && el.closest('select').is(':disabled');
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'ListView',
    match: function(el) { return el.is('ul') || el.is('ol'); },
    empty: function(el) {
      return el.find('li').length == 0;
    }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'TextField',
    match: function(el) { return el.is('textarea') || (el.is('input') && el.attr('type') === 'text'); }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'EmailField',
    match: function(el) { return el.is('input') && el.attr('type') === 'email'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'PasswordField',
    match: function(el) { return el.is('input') && el.attr('type') === 'password'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'PhoneField',
    match: function(el) { return el.is('input') && el.attr('type') === 'tel'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'URLField',
    match: function(el) { return el.is('input') && el.attr('type') === 'url'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'SearchField',
    match: function(el) { return el.is('input') && el.attr('type') === 'search'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'NumberField',
    match: function(el) { return el.is('input') && el.attr('type') === 'number'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'RangeField',
    match: function(el) { return el.is('input') && el.attr('type') === 'range'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'ColorField',
    match: function(el) { return el.is('input') && el.attr('type') === 'color'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'FileDialog',
    match: function(el) { return el.is('input') && el.attr('type') === 'file'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'MonthField',
    match: function(el) { return el.is('input') && el.attr('type') === 'month'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'WeekField',
    match: function(el) { return el.is('input') && el.attr('type') === 'week'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'DateField',
    match: function(el) { return el.is('input') && el.attr('type') === 'date'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'TimeField',
    match: function(el) { return el.is('input') && el.attr('type') === 'time'; }
  }));

  cartridge.components.push($.extend({}, field, {
    type: 'DateTimeField',
    match: function(el) { return el.is('input') && el.attr('type') === 'datetime'; }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Article',
    match: function(el) { return el.is('article'); }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Aside',
    match: function(el) { return el.is('aside'); }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Footer',
    match: function(el) { return el.is('footer'); }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Header',
    match: function(el) { return el.is('header'); }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Section',
    match: function(el) { return el.is('section'); }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Paragraph',
    match: function(el) { return el.is('p'); },
    text: function(el) {
      return el.text().trim();
    }
  }));

  cartridge.components.push($.extend({}, base, {
    type: 'Heading',
    match: function(el) { return el.is('h1') || el.is('h2') || el.is('h3') || el.is('h4') || el.is('h5') || el.is('h6'); },
    text: function(el) {
      return el.text().trim();
    }
  }));

}(window));