/*
 * Copyright (c) 2014 Magnet Systems, Inc.
 * All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you
 * may not use this file except in compliance with the License. You
 * may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied. See the License for the specific language governing
 * permissions and limitations under the License.
 */

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.magnet.plugin.ui.tab;

import com.magnet.plugin.constants.FormConfig;
import com.magnet.plugin.messages.Rest2MobileMessages;

import javax.swing.*;

public class PathPanelLabel extends BasePanel {

    {
        JLabel path = new JLabel();
        JLabel variableLabel = new JLabel();
        JLabel variableNameLabel = new JLabel();

        path.setText(Rest2MobileMessages.getMessage(Rest2MobileMessages.PATH_PART_NAME));
        variableLabel.setText(Rest2MobileMessages.getMessage(Rest2MobileMessages.PATH_VARIABLE_CHECKBOX_NAME));
        variableNameLabel.setText(Rest2MobileMessages.getMessage(Rest2MobileMessages.PATH_VARIABLE_NAME));

        path.setFont(baseFont);
        variableLabel.setFont(baseFont);
        variableNameLabel.setFont(baseFont);

        GroupLayout jPanel2Layout = new GroupLayout(this);
        this.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createSequentialGroup()
                .addComponent(path, GroupLayout.DEFAULT_SIZE, FormConfig.CUSTOM_PREF_SIZE, Short.MAX_VALUE)
                .addComponent(variableLabel, GroupLayout.DEFAULT_SIZE, FormConfig.PATH_CHECKBOX_PREF_SIZE, FormConfig.PATH_CHECKBOX_MAX_SIZE)
                .addComponent(variableNameLabel, GroupLayout.DEFAULT_SIZE, FormConfig.CUSTOM_PREF_SIZE, Short.MAX_VALUE));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(path)
                .addComponent(variableLabel)
                .addComponent(variableNameLabel));
    }
}
