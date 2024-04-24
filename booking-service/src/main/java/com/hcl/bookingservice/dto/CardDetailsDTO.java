package com.hcl.bookingservice.dto;

import java.util.Objects;

public class CardDetailsDTO
{
    private String iban;

    public CardDetailsDTO()
    {
    }

    public CardDetailsDTO(String iban)
    {
        this.iban = iban;
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
        return Objects.equals(iban, that.iban);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(iban);
    }

    @Override
    public String toString()
    {
        return "CardDetailsDTO{" +
                "iban='" + iban + '\'' +
                '}';
    }
}
