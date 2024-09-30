// kontrakt miedzy hashcodem a equalsem

package com.example.secretapp.entity;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.SuperBuilder;


@Getter
@Setter
@Entity
@SuperBuilder
@NoArgsConstructor
@Table(name = "Knowledge")
public class Knowledge extends CommonValues{}