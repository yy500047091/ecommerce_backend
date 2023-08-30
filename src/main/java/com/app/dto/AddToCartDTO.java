package com.app.dto;

import javax.validation.constraints.NotNull;

import lombok.Data;



@Data
public class AddToCartDTO {

   private Integer id;
   private @NotNull Long productId;
   private @NotNull Integer quantity;
   
}
