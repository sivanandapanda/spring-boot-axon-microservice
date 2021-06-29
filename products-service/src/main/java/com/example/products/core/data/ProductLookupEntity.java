package com.example.products.core.data;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Data
@Entity
@Table(name="product_lookup")
public class ProductLookupEntity implements Serializable {
    private static final long serialVersionUID = -3592916752102256394L;

    @Id
    private String productId;
    private String title;
}
