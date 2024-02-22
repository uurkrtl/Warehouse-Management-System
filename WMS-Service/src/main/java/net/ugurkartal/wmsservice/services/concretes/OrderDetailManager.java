package net.ugurkartal.wmsservice.services.concretes;

import lombok.RequiredArgsConstructor;
import net.ugurkartal.wmsservice.models.Order;
import net.ugurkartal.wmsservice.models.OrderDetail;
import net.ugurkartal.wmsservice.models.Product;
import net.ugurkartal.wmsservice.repositories.OrderDetailRepository;
import net.ugurkartal.wmsservice.repositories.OrderRepository;
import net.ugurkartal.wmsservice.repositories.ProductRepository;
import net.ugurkartal.wmsservice.services.abstracts.GenerateIDService;
import net.ugurkartal.wmsservice.services.abstracts.OrderDetailService;
import net.ugurkartal.wmsservice.services.dtos.OrderDetailDto;
import net.ugurkartal.wmsservice.services.mappers.OrderDetailMapper;
import net.ugurkartal.wmsservice.services.requests.OrderDetailCreateRequest;
import net.ugurkartal.wmsservice.services.requests.OrderDetailUpdateRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderDetailManager implements OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderDetailMapper orderDetailMapper;
    private final GenerateIDService generateIDService;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;

    @Override
    public List<OrderDetailDto> getAll() {
        List<OrderDetail> orderDetails = this.orderDetailRepository.findAll();
        List<OrderDetailDto> orderDetailDtos = orderDetails.stream().map(orderDetail -> orderDetailMapper.orderDetailToOrderDetailDtoMapper(orderDetail)).collect(Collectors.toList());
        return orderDetailDtos;
    }

    @Override
    public OrderDetailDto getById(String id) {
        OrderDetail orderDetail = this.orderDetailRepository.findById(id).orElse(null);
        OrderDetailDto orderDetailDto = this.orderDetailMapper.orderDetailToOrderDetailDtoMapper(orderDetail);
        return orderDetailDto;
    }

    @Override
    public OrderDetailDto add(OrderDetailCreateRequest orderDetailCreateRequest) {
        OrderDetail orderDetail = this.orderDetailMapper.createRequestToOrderDetailMapper(orderDetailCreateRequest);
        Order foundOrder = this.orderRepository.findById(orderDetailCreateRequest.getOrderId()).orElse(null);
        Product foundProduct = this.productRepository.findById(orderDetailCreateRequest.getProductId()).orElse(null);

        String newId = this.generateIDService.generateOrderDetailId();
        orderDetail.setId(newId);
        orderDetail.setOrder(foundOrder);
        orderDetail.setProduct(foundProduct);
        orderDetail.setPrice(foundProduct.getSalePrice());
        orderDetail.setTotalPrice(orderDetail.getQuantity() * orderDetail.getPrice());
        orderDetail.setActive(true);

        orderDetail = this.orderDetailRepository.save(orderDetail);
        return this.orderDetailMapper.orderDetailToOrderDetailDtoMapper(orderDetail);
    }

    @Override
    public OrderDetailDto update(String id, OrderDetailUpdateRequest orderDetailUpdateRequest) {
        OrderDetail foundOrderDetail = this.orderDetailRepository.findById(id).orElse(null);

        foundOrderDetail.setQuantity(orderDetailUpdateRequest.getQuantity());
        foundOrderDetail.setActive(orderDetailUpdateRequest.isActive());
        foundOrderDetail.setTotalPrice(foundOrderDetail.getQuantity() * foundOrderDetail.getPrice());

        foundOrderDetail = this.orderDetailRepository.save(foundOrderDetail);
        return this.orderDetailMapper.orderDetailToOrderDetailDtoMapper(foundOrderDetail);
    }

    @Override
    public boolean delete(String id) {
        this.orderDetailRepository.deleteById(id);
        return true;
    }
}