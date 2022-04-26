package ru.geekbrains.march.market.core.endpoints;

import lombok.RequiredArgsConstructor;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;
import ru.geekbrains.march.market.api.ProductDto;
import ru.geekbrains.march.market.core.converters.ProductConverter;
import ru.geekbrains.march.market.core.entities.Product;
import ru.geekbrains.march.market.core.exceptions.ResourceNotFoundException;
import ru.geekbrains.march.market.core.services.ProductService;
import ru.geekbrains.march.market.core.products.GetAllProductsRequest;
import ru.geekbrains.march.market.core.products.GetAllProductsResponse;
import ru.geekbrains.march.market.core.products.GetProductByIdRequest;
import ru.geekbrains.march.market.core.products.GetProductByIdResponse;

import java.util.List;

@Endpoint
@RequiredArgsConstructor
public class ProductEndpoint {
    private static final String NAMESPACE_URI = "http://www.geekbrains.ru/march/market/products";
    private final ProductService productService;
    private final ProductConverter productConverter;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getProductByIdRequest")
    @ResponsePayload
    public GetProductByIdResponse getStudentByName(@RequestPayload GetProductByIdRequest request) {
        GetProductByIdResponse response = new GetProductByIdResponse();
        ProductDto productDto = productConverter.entityToDto(productService.findById(request.getId()).orElseThrow(() -> new ResourceNotFoundException("Продукт с id: " + request.getId() + " не найден")));
        response.setProductDto(productDto);
        return response;
    }


    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getAllProductsRequest")
    @ResponsePayload
    public GetAllProductsResponse getAllStudents(@RequestPayload GetAllProductsRequest request) {
        GetAllProductsResponse response = new GetAllProductsResponse();
        List<Product> products = productService.findAll();
        for (Product product: products) {
            response.getProductDtos().add(productConverter.entityToDto(product));
        }
        return response;
    }
}
