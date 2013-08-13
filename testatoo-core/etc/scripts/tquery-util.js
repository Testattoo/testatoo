/*
 * Copyright (C) 2008 Ovea <dev@testatoo.org>
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
(function(tQuery) {

    tQuery.fn.extend({
        htmlType: function() {
            return tQuery.htmlElementType(this);
        },

        componentType: function() {
            return tQuery.coreComponentType(this);
        },

        attributeValue: function(attributeName) {
            return tQuery.attributeValue(this, attributeName);
        }
    });

    tQuery.attributeValue = function(el, attributeName) {
        var attributeValue;
        if (attributeName == 'value' && el.is('button') && tQuery.browser.msie) {
            var label = el.attr(attributeName);
            el.text('');
            attributeValue = el.attr(attributeName);
            el.attr(attributeName, label);
        } else {
            attributeValue = el.attr(attributeName);
        }
        return attributeValue;
    };

    tQuery.coreComponentType = function(el) {
        if (el.is('button'))
            return 'Button';

        if (el.is('textarea'))
            return 'TextField';

        if (el.is('img'))
            return 'Image';

        if (el.is('a'))
            return 'Link';

        if (el.is('div'))
            return 'Panel';

        if (el.is('select')) {
            if (el.attr('multiple'))
                return 'ListBox';
            return 'DropDown';
        }

        if (el.is('input')) {
            var type = el.attr('type').toLowerCase();

            if (type == 'radio')
                return 'Radio';

            if (type == 'checkbox')
                return 'CheckBox';

            if (type == 'text')
                return 'TextField';

            if (type == 'password')
                return 'PasswordField';

            if (type == 'file')
                return 'FileDialog';

            if (type == 'button' || type == 'submit' || type == 'reset' || type == 'image')
                return 'Button';
        }

        if (el.is('table'))
            return 'DataGrid';

        if (el.is('tr'))
            return 'Row';

        if (el.is('td'))
            return 'Cell';

        if (el.is('th'))
            return 'Column';

        return 'Undefined';
    };

    tQuery.htmlElementType = function(el) {
        if (el.is('img'))
            return 'Img';

        if (el.is('p'))
            return 'P';

        if (el.is('input')) {
            var type = el.attr('type').toLowerCase();

            if (type == 'radio')
                return 'Radio';

            if (type == 'checkbox')
                return 'Checkbox';

            if (type == 'text')
                return 'InputText';

            if (type == 'password')
                return 'InputPassword';

            if (type == 'file')
                return 'File';

            if (type == 'hidden')
                return 'Hidden';

            if (type == 'button' || type == 'submit' || type == 'reset' || type == 'image')
                return 'Button';
        }

        if (el.is('select')) {
            if (el.attr('multiple'))
                return 'ListBox';
            return 'DropDown';
        }
        return el[0].tagName.toLowerCase();
    };
})
        (tQuery);

(function(tQuery) {
    tQuery.fn.extend({
        isTQueryAvailable: function() {
            return true;
        }
    });
})(tQuery);
