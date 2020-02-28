package com.psawesome.testcodeproject.create_xml_documentBuilderFactory;

import com.mongodb.event.CommandSucceededEvent;
import com.psawesome.testcodeproject.create_xml_documentBuilderFactory.custom.CreatedElement;
import org.junit.jupiter.api.Test;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.transform.*;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.StringWriter;
import java.util.List;
import java.util.function.Consumer;

/**
 * @author pilseong
 * @version 1.0
 * @description
 * @see == 개정이력(Modification Information) ==
 * <p>
 * 수정일             수정자            수정내용
 * ------          --------      --------------------------
 * @since 2020-02-28
 */

public class CreatedXmlRunner {

    Document d = null;

    @Test
    void runCreatedXML() {

        CreatedElement ce = new CreatedElement(
                List.of("RSS", "Collection", "MorphemeAnalysis", "DocumentSet", "MultiGroup"));
        Element rss = ce.getElement("RSS");
        Element collection = ce.getElement("Collection");
        Element morphemeAnalysis = ce.getElement("MorphemeAnalysis");
        Element documentSet = ce.getElement("DocumentSet");
        Element multiGroup = ce.getElement("MultiGroup");
        ce.setRootElement(rss)
                .createDomTree(collection)
                .stepEnd()
        .setRootElement(collection)
                .createDomTree(documentSet, morphemeAnalysis, multiGroup)
                .stepEnd()
            .setRootElement(documentSet)
                    .createDomTree(ce.putAndGetElement("Document"))
                    .stepEnd()
                .setRootElement(ce.getElement("Document"))
                        .createDomTree(ce.putAndGetElement("Field"))
                        .stepEnd()
                .setRootElement(ce.getElement("Field"))
                        .stepEnd(ce, this::makeDocumentFields)
                        .stepRemoveRecentElementEnd()
            .setRootElement(morphemeAnalysis)
                .createDomTree(ce.putAndGetElement("Field"))
                .stepEnd(ce, this::makeMorphemeFields)
                .stepRemoveRecentElementEnd()

        ;



        Element documentSet = d.createElement("DocumentSet");
        Element document = d.createElement("Document");
        Element docField = d.createElement("Field");


        document.appendChild(d.createElement("Uid"));
        document.appendChild(d.createElement("Rank"));
        document.appendChild(d.createElement("Weight"));
        document.appendChild(d.createElement("SearcherId"));
        document.appendChild(d.createElement("CollectionId"));
        document.appendChild(d.createElement("DuplicateDocumentCount"));
//        document.appendChild(docField);

        documentSet.appendChild(d.createElement("Count"));
        documentSet.appendChild(d.createElement("TotalCount"));
//        documentSet.appendChild(document);

        collection.appendChild(d.createElement("Id"));
        collection.appendChild(d.createElement("original"));
//        collection.appendChild(documentSet);

        rss.appendChild(d.createElement("Version"));
        rss.appendChild(d.createElement("SuggestedQuery"));
//        rss.appendChild(collection);

        my.createDomTree(rss,
                d.createElement("Version"),
                d.createElement("SuggestedQuery"),
                collection)
                .createDomTree(collection, documentSet, morphemeAnalysis)
                .createDomTree(documentSet, document)
                .createDomTree(document, docField)
                .createDomTree(morphemeAnalysis, morphemeField);


        TransformerFactory tf = TransformerFactory.newInstance();
        Transformer t = null;
        try {
            t = tf.newTransformer();
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        }
        assert t != null;

        t.setOutputProperty(OutputKeys.ENCODING, "utf-8");
        t.setOutputProperty(OutputKeys.METHOD, "xml");
        t.setOutputProperty(OutputKeys.INDENT, "yes");
        t.setOutputProperty(OutputKeys.CDATA_SECTION_ELEMENTS, "yes");

        StringWriter sw = new StringWriter();
        try {
            t.transform(new DOMSource(rss), new StreamResult(sw));
        } catch (TransformerException e) {
            e.printStackTrace();
        }
        System.out.println(sw.toString());

    }

    private void makeDocumentFields(CreatedElement createdElement) {
        createdElement.appendChild("DOCID", "2732145")
                .appendChild("TOTAL_ID", "퉤스트")
                .appendChild("ARTICLE_ID", "본문")
                .appendChild("SOURCE_CODE", "본문")
                .appendChild("VIEW_FLAG", "본문")
                .appendChild("ART_TYPE", "본문")
                .appendChild("SERVICE_DAY", "본문")
                .appendChild("SERVICE_TIME", "본문")
                .appendChild("CONTENT_TYPE", "본문")
                .appendChild("PRESSDATE", "본문")
                .appendChild("PRESSCATEGORY", "본문")
                .appendChild("PRESSMYUN", "본문")
                .appendChild("ART_CRE_TIME", "본문")
                .appendChild("ART_UPD_TIME", "본문")
                .appendChild("SOURCE_NAME", "본문")
                .appendChild("SEARCH_CODE", "본문")
                .appendChild("PORTAL_CODE", "본문")
                .appendChild("MEDIA_CODE", "본문")
                .appendChild("MASTER_CODE_LIST", "본문")
                .appendChild("SERVICE_CODE", "본문")
                .appendChild("SECTION_NAME", "본문")
                .appendChild("SERVICE_CODE_LIST", "본문")
                .appendChild("SECTION_NAME_LIST", "본문")
                .appendChild("ART_THUMB", "본문")
                .appendChild("ART_TITLE", "본문")
                .appendChild("MOB_TITLE", "본문")
                .appendChild("ART_REPORTER", "본문")
        ;
    }

    private void makeMorphemeFields(CreatedElement createdElement) {
        createdElement
                .appendChild("ART_TITLE", "my Title!!")
                .appendChild("MOB_TITLE", "MOB TITLE")
                .appendChild("ART_KWD", "ART KWD")
                .appendChild("ART_REPORTER", "ART REPORTER")
                .appendChild("ART_SUBTITLE", "ART SUB TITLE");
    }


}
