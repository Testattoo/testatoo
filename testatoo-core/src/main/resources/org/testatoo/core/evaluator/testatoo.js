/*
 * Copyright (C) 2008 Ovea <dev@ovea.com>
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
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

    function getType(el) {
        if (el.is('button')) return 'Button';
        if (el.is('textarea')) return 'TextField';
        if (el.is('img')) return 'Image';
        if (el.is('a')) return 'Link';
        if (el.is('div')) return 'Panel';
        if (el.is('ul')) return 'List';
        if (el.is('ol')) return 'List';
        if (el.is('li')) return 'Item';
        if (el.is('select')) return (el.attr('multiple') || el.prop('size') > 0) ? 'ListBox' : 'DropDown';
        if (el.is('option')) return 'Item';
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
        return 'Undefined';
    }

    function dispatchEvent(el, type, evt) {
        if (el.dispatchEvent) {
            el.dispatchEvent(evt);
        } else if (el.fireEvent) {
            el.fireEvent('on' + type, evt);
        }
        return evt;
    }

    function mouseEvent(type, options) {
        var evt;
        var e = $.extend({
            bubbles: true, cancelable: (type != "mousemove"), view: window, detail: 0,
            screenX: 0, screenY: 0, clientX: 0, clientY: 0,
            ctrlKey: false, altKey: false, shiftKey: false, metaKey: false,
            button: 0, relatedTarget: undefined
        }, options);
//        var relatedTarget = $(e.relatedTarget)[0];
        if ($.isFunction(document.createEvent)) {
            evt = document.createEvent("MouseEvents");
            evt.initMouseEvent(type, e.bubbles, e.cancelable, e.view, e.detail,
                e.screenX, e.screenY, e.clientX, e.clientY,
                e.ctrlKey, e.altKey, e.shiftKey, e.metaKey,
                e.button, e.relatedTarget || document.body.parentNode);
        } else if (document.createEventObject) {
            evt = document.createEventObject();
            $.extend(evt, e);
            evt.button = { 0: 1, 1: 4, 2: 2 }[evt.button] || evt.button;
        }
        return evt;
    }

    function keyboardEvent(type, options) {
        var evt, e = $.extend({bubbles: true, cancelable: true, view: window,
            ctrlKey: false, altKey: false, shiftKey: false, metaKey: false,
            keyCode: 0, charCode: 0
        }, options);
        if (/^textInput/.test(type)) {
            evt = document.createEvent('TextEvent');
            evt.initTextEvent(type, true, true, null, String.fromCharCode(options.charCode));
            return evt;
        }
        if ($.isFunction(document.createEvent)) {
            try {
                evt = document.createEvent("KeyEvents");
                evt.initKeyEvent(type, e.bubbles, e.cancelable, e.view,
                    e.ctrlKey, e.altKey, e.shiftKey, e.metaKey,
                    e.keyCode, e.charCode);
                alert('1');
            } catch (err) {
                alert('2');
                try {
                    evt = document.createEvent("Events");
                    alert('3');
                } catch (err) {
                    alert('4');
                    evt = document.createEvent("UIEvents");
                }
                evt.initEvent(type, e.bubbles, e.cancelable);
                $.extend(evt, {view: e.view,
                    ctrlKey: e.ctrlKey, altKey: e.altKey, shiftKey: e.shiftKey, metaKey: e.metaKey,
                    keyCode: e.keyCode, charCode: e.charCode
                });
            }
        } else if (document.createEventObject) {
            evt = document.createEventObject();
            $.extend(evt, e);
        }
        return evt;
    }

    function findCenter(el) {
        var el = $(el), o = el.offset();
        return {
            x: o.left + el.outerWidth() / 2,
            y: o.top + el.outerHeight() / 2
        };
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

        simulate: {

            click: function (id, options) {
                var el = document.getElementById(id);
                dispatchEvent(el, 'mousedown', mouseEvent('mousedown', options));
                dispatchEvent(el, 'mouseup', mouseEvent('mouseup', options));
                dispatchEvent(el, 'click', mouseEvent('click', options));
            },

            rightClick: function (id, options) {
                var el = document.getElementById(id);
                dispatchEvent(el, 'mousedown', mouseEvent('mousedown', options));
                dispatchEvent(el, 'mouseup', mouseEvent('mouseup', options));
                dispatchEvent(el, 'contextmenu', mouseEvent('contextmenu', options));
            },

            dblClick: function (id, options) {
                var el = document.getElementById(id);
                dispatchEvent(el, 'mousedown', mouseEvent('mousedown', options));
                dispatchEvent(el, 'mouseup', mouseEvent('mouseup', options));
                dispatchEvent(el, 'click', mouseEvent('click', options));
                dispatchEvent(el, 'mousedown', mouseEvent('mousedown', options));
                dispatchEvent(el, 'mouseup', mouseEvent('mouseup', options));
                dispatchEvent(el, 'click', mouseEvent('click', options));
                dispatchEvent(el, 'dblclick', mouseEvent('dblclick', options));
            },

            mouseOver: function (id, options) {
                var el = document.getElementById(id);
                dispatchEvent(el, 'mouseover', mouseEvent('mouseover', options));
            },

            mouseOut: function (id, options) {
                var el = document.getElementById(id);
                dispatchEvent(el, 'mouseout', mouseEvent('mouseout', options));
            },

            drag: function (from, to) {
                var origin = document.getElementById(from);
                var target = document.getElementById(to);

                var fromCenter = findCenter(origin);
                var toCenter = findCenter(target);

                var x = Math.floor(fromCenter.x);
                var y = Math.floor(fromCenter.y);

                var xDest = Math.floor(toCenter.x);
                var yDest = Math.floor(toCenter.y);

                var stepX = (xDest - x) / 10;
                var stepY = (yDest - y) / 10;
                stepX = (stepX == 0) ? 1 : stepX;
                stepY = (stepY == 0) ? 1 : stepY;

                var coord = {clientX: x, clientY: y};
                dispatchEvent(origin, 'mousedown', mouseEvent('mousedown', coord));
                while ((Math.abs(xDest - x) > Math.abs(stepX)) || (Math.abs(yDest - y) > Math.abs(stepY))) {
                    if (Math.abs(xDest - x) > Math.abs(stepX)) {
                        x += stepX;
                    }
                    if (Math.abs(yDest - y) > Math.abs(stepY)) {
                        y += stepY;
                    }
                    coord = { clientX: x, clientY: y };
                    dispatchEvent(origin, 'mousemove', mouseEvent('mousemove', coord));
                }
                dispatchEvent(origin, 'mousemove', mouseEvent('mousemove', coord));
                dispatchEvent(origin, 'mouseup', mouseEvent('mouseup', coord));
                dispatchEvent(target, 'mouseup', mouseEvent('mouseup', coord));
            },

            type: function (id, options) {
                var el = document.getElementById(id);
                dispatchEvent(el, 'keydown', keyboardEvent('keydown', options));
                dispatchEvent(el, 'keypress', keyboardEvent('keypress', options));
                if (webkit) {
                    dispatchEvent(el, 'textInput', keyboardEvent('textInput', options));
                }
                dispatchEvent(el, 'keyup', keyboardEvent('keyup', options));
            }

        },

        check: function (id) {
            var el = $('#' + id + '');
            if (el.is('input') && (el.attr('type') == 'radio' || el.attr('type') == 'checkbox') && !el.prop('checked')) {
                testatoo.ext.simulate.click(id);
            }
        },

        selectItem: function (id) {
            $('#' + id + '').prop('selected', true);
        },

        unSelectItem: function (id) {
            $('#' + id + '').prop('selected', false);
        },

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

        getSize: function (id) {
            var el = $('#' + id + '');
            if (el.is('ul')) {
                return el.find('li').length;
            }
            if (el.is('select')) {
                return el.find('option').length;
            }
            if (el.is('optgroup')) {
                return el.find('option').length;
            }
            return 0;
        },

        getText: function (id) {
            var el = $('#' + id + '');
            return (el.prop('nodeName') || '').toLowerCase() == 'input' ? el.val() : el.text().trim();
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

        isDisabled: function(id) {
            var el = $('#' + id + '');
            if(el.is(':disabled')) {
                return true;
            }
            return (el.is('option') || el.is('optgroup')) && el.closest('select').is(':disabled');
        },

        isMultiSelectable: function(id) {
            var el = $('#' + id + '');
            return el.is('select') && el.prop('multiple');
        },

        type: function (id, text) {
            var el = $('#' + id + '');
            el.val('');
            $.ext.simulate.click(id);
            text = text || '';
            for (var i = 0; i < text.length; i++) {
                $.ext.simulate.type(id, {charCode: text.charCodeAt(i)});
            }
        }

    };

}(window));
