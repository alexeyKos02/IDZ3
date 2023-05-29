package com.alexey.sec.store.restaurant;

import com.alexey.sec.store.user.User;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="_Orders")
public class Order {
    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;
    @Enumerated(EnumType.STRING)
    private statusEnum status;
    @Column( name = "special_requests")
    private String specialRequests;
    @Column( name = "created_at")
    private Timestamp createdAt;
    @Column( name = "updated_at")
    private Timestamp updatedAt;
}
