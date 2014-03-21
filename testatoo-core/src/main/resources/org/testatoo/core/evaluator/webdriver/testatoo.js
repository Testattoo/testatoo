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

    $.options = {
        customType: function(el) {}
    };

    function getType(el) {
        var type = $.options.customType(el);
        if (type) return type;
        if (el.is('button')) return 'Button';
        if (el.is('textarea')) return 'TextField';
        if (el.is('img')) return 'Image';
        if (el.is('a')) return 'Link';
        if (el.is('h1') || el.is('h2') || el.is('h3') || el.is('h4') || el.is('h5') || el.is('h6')) return 'Heading';
        if (el.is('div')) return 'Panel';
        if (el.is('ul')) return 'ListView';
        if (el.is('ol')) return 'ListView';
        if (el.is('select')) return (el.attr('multiple') || el.prop('size') > 0) ? 'ListBox' : 'DropDown';
        if (el.is('option') || el.is('li')) return 'Item';
        if (el.is('optgroup')) return 'GroupItem';
        if (el.is('form')) return 'Form';
        if (el.is('table')) return 'DataGrid';
        if (el.is('tr')) return 'Row';
        if (el.is('td')) return 'Cell';
        if (el.is('th')) return 'Column';
        if (el.is('input')) {
            switch (el.attr('type').toLowerCase() || '') {
                case 'radio':
                    return 'Radio';
                case 'checkbox':
                    return 'CheckBox';
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
                    return 'Undefined';
            }
        }
        return 'Component';
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
            metaInfos.push({
                id: id,
                node: me.prop('nodeName').toLowerCase(),
                type: getType(me)
            });
        });
        return metaInfos;
    };

    $.ext = {

        getLabel: function (id) {
            var el = $('#' + id + '');
            if (el.is('option') || el.is('optgroup'))  {
                return el.attr('label');
            }

            var label = $('label[for="' + id + '"]');
            if (label.length > 0) return label.text();

            var p = el.prev('label');
            if (p.length > 0) return p.text();
            return el.parent().text().trim();
        },

        isEmpty: function (id) {
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
        },

        contains: function(id, ids) {
            var el = $('#' + id + '');
            var not = [];
            $.each(ids, function(index, _id) {
                !$.contains(el[0], $('#' + _id)[0]) && not.push(_id);
            });
            return not;
        }
    };

}(window));
