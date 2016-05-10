/**
 * The MIT License
 * Copyright (c) 2016 Population Register Centre
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package fi.vm.kapa.rova.soap.vtj;

import javax.xml.bind.ValidationEvent;
import javax.xml.bind.ValidationEventHandler;

import fi.vm.kapa.rova.logging.Logger;

public class CustomValidationEventHandler implements ValidationEventHandler {

    private static Logger LOG = Logger.getLogger(CustomValidationEventHandler.class);

    @Override
    public boolean handleEvent(ValidationEvent event) {
        LOG.debug("Event Info: " + event);
        if (event.getMessage().contains("unexpected element")) {
            LOG.debug("Unexpected element found: " + event);
        } else {
            LOG.debug("Validation event: " + event + " " + event.getMessage());
        }
        return true;
    }
}
