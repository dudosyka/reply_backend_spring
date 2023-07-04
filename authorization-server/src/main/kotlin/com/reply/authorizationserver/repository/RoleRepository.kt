package com.reply.authorizationserver.repository

import com.reply.authorizationserver.model.RoleModel
import org.springframework.data.repository.CrudRepository

interface RoleRepository : CrudRepository<RoleModel, Long>