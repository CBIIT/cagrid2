/*
 *  Copyright 2001-2005 Internet2
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

package gov.nih.nci.cagrid.opensaml;

import java.io.InputStream;

import org.w3c.dom.*;

/**
 *  Abstract class for a SAML Subject Statement
 *
 * @author     Scott Cantor
 * @created    March 25, 2002
 */
public abstract class SAMLSubjectStatement extends SAMLStatement implements Cloneable
{
    protected SAMLSubject subject = null;

    /**
     *  Default constructor
     */
    protected SAMLSubjectStatement() {
    }

    /**
     *  Builds a statement out of its component parts
     *
     * @param  subject            Subject of statement
     * @exception  SAMLException  Raised if a statement cannot be constructed
     *      from the supplied information
     */
    protected SAMLSubjectStatement(SAMLSubject subject) throws SAMLException {
        this.subject = (SAMLSubject)subject.setParent(this);
    }

    /**
     *  Reconstructs a subject statement from a DOM tree
     *
     * @param  e                  The root of a DOM tree
     * @exception  SAMLException  Thrown if the object cannot be constructed
     */
    protected SAMLSubjectStatement(Element e) throws SAMLException {
        fromDOM(e);
    }

    /**
     *  Reconstructs a subject statement from a stream
     *
     * @param  in                   A stream containing XML
     * @exception  SAMLException  Raised if an exception occurs while constructing
     *                              the object.
     */
    protected SAMLSubjectStatement(InputStream in) throws SAMLException {
        fromDOM(fromStream(in));
    }

    /**
     * @see gov.nih.gov.nih.nci.cagrid.opensaml.SAMLObject#fromDOM(org.w3c.dom.Element)
     */
    public void fromDOM(Element e) throws SAMLException {
        super.fromDOM(e);
        
        // Extract subject.
        subject = (SAMLSubject)new SAMLSubject(XML.getFirstChildElement(e)).setParent(this);
    }

    /**
     *  Gets the statement subject
     *
     * @return    The statement subject
     */
    public SAMLSubject getSubject() {
        return subject;
    }

    /**
     *  Sets the statement subject
     *
     * @param subject    The statement subject
     * @exception SAMLException     Raised if the subject is invalid
     */
    public void setSubject(SAMLSubject subject) throws SAMLException {
        if (subject != null) {
            this.subject=(SAMLSubject)subject.setParent(this);
            setDirty(true);
        }
        else
            throw new IllegalArgumentException("subject cannot be null");
    }

    /**
     *  @see gov.nih.gov.nih.nci.cagrid.opensaml.SAMLObject#toDOM(org.w3c.dom.Document,boolean)
     */
    public Node toDOM(Document doc, boolean xmlns) throws SAMLException {
        // Let the base build/verify the DOM root. 
        super.toDOM(doc, xmlns);
        if (dirty) {
            ((Element)root).appendChild(subject.toDOM(doc,false));
        }
        return root;
    }

    /**
     * @see gov.nih.gov.nih.nci.cagrid.opensaml.SAMLObject#checkValidity()
     */
    public void checkValidity() throws SAMLException {
        if (subject == null)
            throw new MalformedException(SAMLException.RESPONDER, "SubjectStatement invalid, requires subject");
    }

    /**
     *  Copies a SAML object such that no dependencies exist between the original
     *  and the copy
     * 
     * @return      The new object
     * @see java.lang.Object#clone()
     */
    public Object clone() throws CloneNotSupportedException {
        SAMLSubjectStatement dup=(SAMLSubjectStatement)super.clone();
        
        try {
            // Clone the embedded objects.
            dup.subject = (SAMLSubject)((SAMLSubject)subject.clone()).setParent(dup);
        }
        catch (SAMLException e) {
            throw new CloneNotSupportedException(e.getMessage());
        }
        
        return dup;
    }
}

