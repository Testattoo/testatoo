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

    w.testatoo.options = {
        customType: function(el) {
            if (el.attr('role') == 'progressbar')
                return 'ProgressBar';
            if (el.hasClass('nav-tabs'))
                return 'TabPanel';
            if (el.is('a') && el.closest('ul').hasClass('nav-tabs'))
                return 'Tab';
            if (el.hasClass('panel-group'))
                return 'Accordion';
            if (el.is('a') && el.closest('div').hasClass('panel-heading'))
                return 'Item';
            return undefined;
        }
    };

}(window));
