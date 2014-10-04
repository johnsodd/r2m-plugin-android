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
import com.magnet.plugin.helpers.HintHelper;
import com.magnet.plugin.helpers.Rest2MobileConstants;
import com.magnet.plugin.helpers.UIHelper;
import com.magnet.plugin.helpers.VerifyHelper;
import com.magnet.plugin.models.PathPart;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusListener;

import static com.magnet.plugin.helpers.UIHelper.ERROR_REQUIRED_FIELD;

public class PathPartPanel extends JPanel {

    private JTextField pathPartField;
    private JCheckBox isVariableCheckBox;
    private JTextField variableNameField;
    private JButton deleteButton;

    private String variableNameValue;

    private JPanel parentPanel;

    public PathPartPanel(JPanel parentPanel, PathPart pathPartPartField) {
        this.parentPanel = parentPanel;
        variableNameField.setText(pathPartPartField.getTemplateVariable());
        isVariableCheckBox.setSelected(pathPartPartField.isTemplatized());
        this.pathPartField.setText(pathPartPartField.getPathPart());
    }


    {
        pathPartField = new JTextField();
        isVariableCheckBox = new JCheckBox();
        variableNameField = new JTextField();
        deleteButton = new JButton("X");

        variableNameValue = "";

        Font font = UIHelper.getFont();
        pathPartField.setFont(font);
        isVariableCheckBox.setFont(font);
        variableNameField.setFont(font);
        deleteButton.setFont(font);

        HintHelper.setHintToTextField(ERROR_REQUIRED_FIELD, pathPartField);

        isVariableCheckBox.setSelected(false);
        variableNameField.setEditable(false);

        isVariableCheckBox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                changeVariable(evt);
            }
        });

        deleteButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                deleteThisPanel();
            }
        });


        GroupLayout jPanel2Layout = new GroupLayout(this);
        this.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(pathPartField, GroupLayout.DEFAULT_SIZE, FormConfig.CUSTOM_PREF_SIZE, Short.MAX_VALUE)
                                .addComponent(isVariableCheckBox, GroupLayout.DEFAULT_SIZE, FormConfig.PATH_CHECKBOX_PREF_SIZE, FormConfig.PATH_CHECKBOX_MAX_SIZE)
                                .addComponent(variableNameField, GroupLayout.DEFAULT_SIZE, FormConfig.CUSTOM_PREF_SIZE, Short.MAX_VALUE)
                                .addComponent(deleteButton)));
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(GroupLayout.Alignment.LEADING, jPanel2Layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                                .addComponent(pathPartField)
                                .addComponent(isVariableCheckBox)
                                .addComponent(variableNameField)
                                .addComponent(deleteButton)));
    }

    private void changeVariable(ActionEvent evt) {
        if (isVariableCheckBox.isSelected()) {
            variableNameField.setEditable(true);
            variableNameField.setText(variableNameValue);
            HintHelper.setHintToTextField(UIHelper.ERROR_REQUIRED_FIELD, variableNameField);
        } else {
            HintHelper.removeHintFromField(variableNameField);
            variableNameField.setEditable(false);
            variableNameValue = variableNameField.getText().trim();
            variableNameField.setText("");
        }
    }

    public void deleteThisPanel() {
        parentPanel.remove(this);
        if (parentPanel instanceof URLSection) {
            ((URLSection) parentPanel).removePath(this);
        }
        parentPanel.revalidate();
        parentPanel.validate();
        parentPanel.repaint();
    }

    public PathPart getPathPartField() {
        PathPart pathPart = new PathPart();
        pathPart.setPathPart(this.pathPartField.getText());
        pathPart.setTemplatized(isVariableCheckBox.isSelected());
        pathPart.setVariableName(Rest2MobileConstants.START_TEMPLATE_VARIABLE + variableNameField.getText() + Rest2MobileConstants.END_TEMPLATE_VARIABLE);

        return pathPart;
    }

    public void setFocusListener(FocusListener focusListener) {
        pathPartField.addFocusListener(focusListener);
        variableNameField.addFocusListener(focusListener);
        deleteButton.addFocusListener(focusListener);
    }

    public void invalidateField() {
        variableNameField.setText(VerifyHelper.verifyVariableName(variableNameField.getText()));
    }

    public boolean checkRequirementField() {
        if (pathPartField.getText().toString().trim().equalsIgnoreCase("")) {
            return false;
        }

        if (isVariableCheckBox.isSelected()) {
            if (variableNameField.getText().trim().equalsIgnoreCase("")) {
                return false;
            }
        }

        return true;
    }

}
