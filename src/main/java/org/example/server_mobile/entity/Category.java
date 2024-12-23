package org.example.server_mobile.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedDate;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@FieldDefaults(level = lombok.AccessLevel.PRIVATE)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;
    String name;
    String description;
    @ManyToOne
    @JoinColumn(name = "parent_cat_id")
    Category parentCatId;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    List<Product> products;
    @OneToMany(mappedBy = "parentCatId", cascade = CascadeType.ALL)
    List<Category> subCategories;
    @ManyToOne
    @JoinColumn(name = "age_group_id")
    AgeGroup age;
    @ManyToOne
    @JoinColumn(name = "gender_id")
    Gender gender;
    @CreatedDate
    @UpdateTimestamp
    Date createdAt;
    @UpdateTimestamp
    Date updatedAt;

}
