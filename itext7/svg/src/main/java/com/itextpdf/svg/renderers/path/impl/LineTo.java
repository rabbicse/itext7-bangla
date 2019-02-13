/*
    This file is part of the iText (R) project.
    Copyright (c) 1998-2019 iText Group NV
    Authors: iText Software.

    This program is free software; you can redistribute it and/or modify
    it under the terms of the GNU Affero General Public License version 3
    as published by the Free Software Foundation with the addition of the
    following permission added to Section 15 as permitted in Section 7(a):
    FOR ANY PART OF THE COVERED WORK IN WHICH THE COPYRIGHT IS OWNED BY
    ITEXT GROUP. ITEXT GROUP DISCLAIMS THE WARRANTY OF NON INFRINGEMENT
    OF THIRD PARTY RIGHTS

    This program is distributed in the hope that it will be useful, but
    WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY
    or FITNESS FOR A PARTICULAR PURPOSE.
    See the GNU Affero General Public License for more details.
    You should have received a copy of the GNU Affero General Public License
    along with this program; if not, see http://www.gnu.org/licenses or write to
    the Free Software Foundation, Inc., 51 Franklin Street, Fifth Floor,
    Boston, MA, 02110-1301 USA, or download the license from the following URL:
    http://itextpdf.com/terms-of-use/

    The interactive user interfaces in modified source and object code versions
    of this program must display Appropriate Legal Notices, as required under
    Section 5 of the GNU Affero General Public License.

    In accordance with Section 7(b) of the GNU Affero General Public License,
    a covered work must retain the producer line in every PDF that is created
    or manipulated using iText.

    You can be released from the requirements of the license by purchasing
    a commercial license. Buying such a license is mandatory as soon as you
    develop commercial activities involving the iText software without
    disclosing the source code of your own applications.
    These activities include: offering paid services to customers as an ASP,
    serving PDFs on the fly in a web application, shipping iText with a closed
    source product.

    For more information, please contact iText Software Corp. at this
    address: sales@itextpdf.com
 */
package com.itextpdf.svg.renderers.path.impl;

import com.itextpdf.io.util.MessageFormatUtil;
import com.itextpdf.kernel.geom.Point;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.styledxmlparser.css.util.CssUtils;
import com.itextpdf.svg.exceptions.SvgExceptionMessageConstant;
import com.itextpdf.svg.utils.SvgCoordinateUtils;

import java.util.Arrays;

/***
 * Implements lineTo(L) attribute of SVG's path element
 * */
public class LineTo extends AbstractPathShape {

    // (x y)+
    protected String[][] coordinates;

    public LineTo() {
        this(false);
    }

    public LineTo(boolean relative) {
        super.relative = relative;
    }

    @Override
    public void draw(PdfCanvas canvas) {
        for (int i = 0; i < coordinates.length; i++) {
            float x = CssUtils.parseAbsoluteLength(coordinates[i][0]);
            float y = CssUtils.parseAbsoluteLength(coordinates[i][1]);
            canvas.lineTo(x, y);
        }
    }

    @Override
    public void setCoordinates(String[] coordinates, Point startPoint) {
        if (coordinates.length == 0 || coordinates.length % 2 != 0) {
            throw new IllegalArgumentException(MessageFormatUtil.format(SvgExceptionMessageConstant.LINE_TO_EXPECTS_FOLLOWING_PARAMETERS_GOT_0, Arrays.toString(coordinates)));
        }
        this.coordinates = new String[coordinates.length / 2][];
        double[] initialPoint = new double[] {startPoint.getX(), startPoint.getY()};
        for (int i = 0; i < coordinates.length; i += 2) {
            String[] curCoordinates = new String[]{coordinates[i], coordinates[i + 1]};
            if (isRelative()) {
                curCoordinates = SvgCoordinateUtils.makeRelativeOperatorCoordinatesAbsolute(curCoordinates, initialPoint);
                initialPoint[0] = (float)CssUtils.parseFloat(curCoordinates[0]);
                initialPoint[1] = (float)CssUtils.parseFloat(curCoordinates[1]);
            }
            this.coordinates[i / 2] = curCoordinates;
        }
    }

    @Override
    public Point getEndingPoint() {
        return createPoint(coordinates[coordinates.length - 1][0], coordinates[coordinates.length - 1][1]);
    }

}
