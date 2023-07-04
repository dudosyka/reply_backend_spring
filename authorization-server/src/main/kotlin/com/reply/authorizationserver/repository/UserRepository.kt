package com.reply.authorizationserver.repository

import com.reply.authorizationserver.model.UserModel
import org.springframework.data.repository.CrudRepository

interface UserRepository : CrudRepository<UserModel, Long> {
    fun findByLogin(login: String): UserModel?
}

