package me.markelm;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.Marshaller;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
        JAXBContext context = JAXBContext.newInstance(Mendia.class);
        Marshaller jaxbMarshaller = context.createMarshaller();

        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);


        jaxbMarshaller.marshal(args, new File("udalaitz.xml"));
        jaxbMarshaller.marshal(args, System.out);
    }
}
