package com.reply.authorizationserver.model

import jakarta.persistence.*
import org.springframework.security.core.GrantedAuthority

@Entity
@Table(name = "RoleModels")
class RoleModel(
    @Column
    val name: String,

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,
) : GrantedAuthority {
    /**
     * If the `GrantedAuthority` can be represented as a `String`
     * and that `String` is sufficient in precision to be relied upon for an
     * access control decision by an [AccessDecisionManager] (or delegate), this
     * method should return such a `String`.
     *
     *
     * If the `GrantedAuthority` cannot be expressed with sufficient precision
     * as a `String`, `null` should be returned. Returning
     * `null` will require an `AccessDecisionManager` (or delegate)
     * to specifically support the `GrantedAuthority` implementation, so
     * returning `null` should be avoided unless actually required.
     * @return a representation of the granted authority (or `null` if the
     * granted authority cannot be expressed as a `String` with sufficient
     * precision).
     */
    override fun getAuthority(): String {
        return name
    }

    override fun toString(): String {
        return "RoleModel(name='$name', id=$id)"
    }
}