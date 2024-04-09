package com.hcl.bookingservice.dto;

import java.util.Objects;

public class CardDetailsDTO
{
    private Long id;

    private String iban;

    public CardDetailsDTO()
    {
    }

    public CardDetailsDTO(Long id, String iban)
    {
        this.id = id;
        this.iban = iban;
    }

    public Long getId()
    {
        return id;
    }

    public void setId(Long id)
    {
        this.id = id;
    }

    public String getIban()
    {
        return iban;
    }

    public void setIban(String iban)
    {
        this.iban = iban;
    }

    @Override
    public boolean equals(Object o)
    {
        if (this == o)
        {
            return true;
        }
        if (o == null || getClass() != o.getClass())
        {
            return false;
        }
        CardDetailsDTO that = (CardDetailsDTO) o;
        return Objects.equals(id, that.id) && Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(id, iban);
    }

    @Override
    public String toString()
    {
        return "CardDetailsDTO{" +
                "id=" + id +
                ", iban='" + iban + '\'' +
                '}';
    }
}
