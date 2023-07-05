package com.reply.authorizationserver.model

import jakarta.persistence.*

@Entity
@Table(name = "UserRoleModels")
class UserRoleModel(
    @ManyToOne
    @JoinColumn(name = "user_id")
    val user: UserModel,

    @ManyToOne
    @JoinColumn(name = "role_id")
    val role: RoleModel,
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 1
)