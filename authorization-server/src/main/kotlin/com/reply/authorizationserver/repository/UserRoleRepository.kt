package com.reply.authorizationserver.repository

import com.reply.authorizationserver.model.UserRoleModel
import org.springframework.data.repository.CrudRepository

interface UserRoleRepository : CrudRepository<UserRoleModel, Long> {
}