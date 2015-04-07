// HTML5 Cartridge

(function(w) {

  var cartridge = {
    name: 'html5',
    components: []
  };

  w.testatoo.registerCartridge(cartridge);

  var common = {
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
    label: function(el) {
      var label = $('label[for="' + el.attr('id') + '"]');
      if (label.length > 0) return label.text();

      var p = el.prev('label');
      if (p.length > 0) return p.text();
      return el.parent().text().trim();
    },
    checked: function(el) {
      return el.is(':checked');
    },
    unchecked: function(el) {
      return !this.checked(el);
    },
    size: function(el) {
      return el.children().length;
    }
  };

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Panel',
    match: function(el) {
      return el.is('div');
    },
    title: function() {
      return '';
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
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

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Checkbox',
    match: function(el) {
      return el.is('input') && el.attr('type') === 'checkbox';
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Radio',
    match: function(el) {
      return el.is('input') && el.attr('type').toLowerCase() === 'radio';
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'DataGrid',
    match: function(el) {
      return el.is('table');
    },
    columnsize: function(el) {
      if (el.is('table'))
        return el.find('thead tr:last th').length;
      return undefined;
    },
    size: function(el) {
      return el.find('tbody tr').length;
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Column',
    match: function(el) {
      return el.is('th');
    },
    title: function(el) {
      return el.text().trim();
    },
    size: function(el) {
      return el.closest('table').find('tbody tr').find('td:nth-child(' + el.index() + 1 + ')').length;
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Row',
    match: function(el) {
      return el.is('tr');
    },
    size: function(el) {
      return el.find('td').length;
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Cell',
    match: function(el) {
      return el.is('td');
    },
    value: function(el) {
      return el.text().trim();
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Dropdown',
    match: function(el) {
      return el.is('select') && !el.attr('multiple') && !el.prop('size') > 0;
    },
    size: function(el) {
      return el.find('option').length;
    },
    groupitemssize: function(el) {
      return el.find('optgroup').length;
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'ListBox',
    match: function(el) {
      return el.is('select') && (el.attr('multiple') || el.prop('size') > 0);
    },
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

  cartridge.components.push(w.$.extend({}, common, {
    type: 'ListView',
    match: function(el) {
      return el.is('ul') || el.is('ol');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Item',
    match: function(el) {
      return el.is('option') || el.is('li');
    },
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
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'GroupItem',
    match: function(el) {
      return el.is('optgroup');
    },
    label: function(el) {
      return el.attr('label');
    },
    size: function(el) {
      return el.find('option').length;
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Link',
    match: function(el) {
      return el.is('a');
    },
    text: function(el) {
      return el.text().trim();
    },
    reference: function(el) {
      return el.prop('href');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Image',
    match: function(el) {
      return el.is('img');
    },
    source: function(el) {
      return el.prop('src');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'TextField',
    match: function(el) {
      return el.is('textarea') || (el.is('input') && el.attr('type') === 'text');
    },
    text: function(el) {
      return el.val();
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Article',
    match: function(el) {
      return el.is('article');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Aside',
    match: function(el) {
      return el.is('aside');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Footer',
    match: function(el) {
      return el.is('footer');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Header',
    match: function(el) {
      return el.is('header');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Section',
    match: function(el) {
      return el.is('section');
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Paragraph',
    match: function(el) {
      return el.is('p');
    },
    text: function(el) {
      return el.text().trim();
    }
  }));

  cartridge.components.push(w.$.extend({}, common, {
    type: 'Heading',
    match: function(el) {
      return el.is('h1') || el.is('h2') || el.is('h3') || el.is('h4') || el.is('h5') || el.is('h6');
    },
    text: function(el) {
      return el.text().trim();
    }
  }));



}(window));