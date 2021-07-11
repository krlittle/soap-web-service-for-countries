package krlittle.soapwebserviceforcountries.endpoints;

import io.spring.guides.gs_producing_web_service.GetCountryRequest; //generated via jaxb task, build depends on jaxb task
import io.spring.guides.gs_producing_web_service.GetCountryResponse; //generated via jaxb task, build depends on jaxb task
import krlittle.soapwebserviceforcountries.repositories.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

@Endpoint
public class CountryEndpoint {
    private static final String NAMESPACE_URI = "http://spring.io/guides/gs-producing-web-service";

    private CountryRepository countryRepository;

    @Autowired
    public CountryEndpoint(CountryRepository countryRepository) {
        this.countryRepository = countryRepository;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getCountryRequest")
    @ResponsePayload
    public GetCountryResponse getCountryRequest(@RequestPayload GetCountryRequest request) {
        GetCountryResponse response = new GetCountryResponse();
        response.setCountry(countryRepository.findCountry(request.getName()));
        return response;
    }

    // The @Endpoint annotation registers the class with Spring WS as a potential candidate for processing incoming SOAP messages.
    // The @PayloadRoot annotation is then used by Spring WS to pick the handler method, based on the message’s namespace and localPart.
    // The @RequestPayload annotation indicates that the incoming message will be mapped to the method’s request parameter.
    // The @ResponsePayload annotation makes Spring WS map the returned value to the response payload.
}