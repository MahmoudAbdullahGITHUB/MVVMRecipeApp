package com.example.mvvmrecipeapp.domain.util

interface DomainMapper<Dto, DomainModel> {

    fun mapToDomainModel(model: Dto): DomainModel

    fun mapFromDomainModel(domainModel: DomainModel): Dto
}