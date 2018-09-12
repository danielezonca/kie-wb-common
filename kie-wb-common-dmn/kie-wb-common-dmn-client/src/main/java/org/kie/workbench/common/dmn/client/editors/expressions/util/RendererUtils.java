/*
 * Copyright 2018 Red Hat, Inc. and/or its affiliates.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.workbench.common.dmn.client.editors.expressions.util;

import com.ait.lienzo.client.core.shape.Group;
import com.ait.lienzo.client.core.shape.Text;
import com.ait.lienzo.client.core.shape.TextLineBreakWrap;
import com.ait.lienzo.shared.core.types.TextAlign;
import com.google.gwt.core.client.GWT;
import org.kie.workbench.common.dmn.api.property.dmn.QName;
import org.kie.workbench.common.dmn.client.editors.expressions.types.context.InformationItemCell;
import org.kie.workbench.common.dmn.client.widgets.grid.BaseExpressionGridTheme;
import org.kie.workbench.common.dmn.client.widgets.grid.columns.NameAndDataTypeHeaderMetaData;
import org.uberfire.ext.wires.core.grids.client.model.GridCell;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridBodyCellRenderContext;
import org.uberfire.ext.wires.core.grids.client.widget.context.GridHeaderColumnRenderContext;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.grids.GridRenderer;
import org.uberfire.ext.wires.core.grids.client.widget.grid.renderers.themes.GridRendererTheme;

public class RendererUtils {

    public static final String FONT_STYLE_TYPE_REF = "italic";

    public static final double SPACING = 8.0;

    public static Group getExpressionCellText(final GridBodyCellRenderContext context,
                                              final GridCell<String> gridCell) {
        final GridRenderer gridRenderer = context.getRenderer();
        final GridRendererTheme theme = gridRenderer.getTheme();

        final Group g = GWT.create(Group.class);
        final Text t = theme.getBodyText();
        t.setText(gridCell.getValue().getValue());
        t.setListening(false);
        t.setX(5);
        t.setY(5);
        t.setFontFamily(BaseExpressionGridTheme.FONT_FAMILY_EXPRESSION);
        t.setTextAlign(TextAlign.LEFT);
        t.setWrapper(new TextLineBreakWrap(t));
        g.add(t);

        return g;
    }

    public static Group getCenteredCellText(final GridBodyCellRenderContext context,
                                            final GridCell<String> gridCell) {
        final GridRenderer gridRenderer = context.getRenderer();
        final GridRendererTheme theme = gridRenderer.getTheme();

        final Group g = GWT.create(Group.class);
        final Text t = theme.getBodyText();
        t.setText(gridCell.getValue().getValue());
        t.setListening(false);
        t.setX(context.getCellWidth() / 2);
        t.setY(context.getCellHeight() / 2);
        g.add(t);
        return g;
    }

    public static Group getNameAndDataTypeText(final NameAndDataTypeHeaderMetaData headerMetaData,
                                               final GridHeaderColumnRenderContext context,
                                               final double blockWidth,
                                               final double blockHeight) {
        return getNameAndDataTypeText(context.getRenderer().getTheme(),
                                      headerMetaData.getTitle(),
                                      headerMetaData.getTypeRef(),
                                      blockWidth,
                                      blockHeight);
    }

    public static Group getNameAndDataTypeText(final InformationItemCell.HasNameAndDataTypeCell hasNameAndDataTypeCell,
                                               final GridBodyCellRenderContext context) {
        return getNameAndDataTypeText(context.getRenderer().getTheme(),
                                      hasNameAndDataTypeCell.getName().getValue(),
                                      hasNameAndDataTypeCell.getTypeRef(),
                                      context.getCellWidth(),
                                      context.getCellHeight());
    }

    private static Group getNameAndDataTypeText(final GridRendererTheme theme,
                                                final String name,
                                                final QName typeRef,
                                                final double blockWidth,
                                                final double blockHeight) {
        final Group headerGroup = GWT.create(Group.class);

        final Text tName = theme.getHeaderText();
        tName.setText(name);
        tName.setListening(false);
        tName.setX(blockWidth / 2);
        tName.setY(blockHeight / 2 - SPACING);

        final Text tTypeRef = theme.getHeaderText();
        tTypeRef.setFontStyle(FONT_STYLE_TYPE_REF);
        tTypeRef.setFontSize(BaseExpressionGridTheme.FONT_SIZE - 2.0);
        tTypeRef.setText("(" + typeRef.toString() + ")");
        tTypeRef.setListening(false);
        tTypeRef.setX(blockWidth / 2);
        tTypeRef.setY(blockHeight / 2 + SPACING);

        headerGroup.add(tName);
        headerGroup.add(tTypeRef);

        return headerGroup;
    }
}
