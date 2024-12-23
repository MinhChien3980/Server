package org.example.server_mobile.mapper;

import org.example.server_mobile.dto.request.AddressRequest;
import org.example.server_mobile.dto.response.AddressResponse;
import org.mapstruct.Mapper;
import org.example.server_mobile.entity.Address;


@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toAddress(AddressRequest request);
    AddressResponse toAddressResponse(Address address);
}
