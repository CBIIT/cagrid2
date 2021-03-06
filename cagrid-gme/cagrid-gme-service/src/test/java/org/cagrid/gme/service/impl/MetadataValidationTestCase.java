package org.cagrid.gme.service.impl;


/**
* @author oster
*/
public class MetadataValidationTestCase extends SchemaValidationTestCase {

    /*
     * (non-Javadoc)
     *
     * @see gov.nih.nci.cagrid.cadsr.metadata.SchemaValidationTestCase#getSchemaFilename()
     */
    public String getSchemaFilename() {
        return "src/test/resources/schema/cagrid/caGridMetadata.xsd";
    }


    /*
     * (non-Javadoc)
     *
     * @see gov.nih.nci.cagrid.cadsr.metadata.SchemaValidationTestCase#getXMLFilename()
     */
    public String getXMLFilename() {
        return "src/main/resources/serviceMetadata.xml";
    }

}
