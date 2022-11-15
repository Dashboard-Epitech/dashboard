import { Button, Flex, FormControl, FormLabel, Input, Select } from "@chakra-ui/react"
import { useEffect } from "react";
import { useState } from "react";
import * as ajax from "../../../lib/ajax";
import { SmallCurrency } from "./SmallCurrency";


export const NewCurrency = () => {
    const [from, setFrom] = useState("")
    const [to, setTo] = useState("")
    const [mid, setMid] = useState(0)
    const [currencies, setCurrencies] = useState([])

    const GetAllCurrencies = () => {
        ajax.getAllcurrenciesBrut()
        .then((response) => {
            console.log("good")
            var temp = []
            response.data.currencies.forEach(currency => {
                temp.push(<option value={currency.iso}>{currency.currency_name}</option>)
            });
            setCurrencies(temp)
        })
        .catch((error) => {
            console.log("error")
            console.log(error)
        })
    }

    useEffect(() => {
        if (to == "" || from == "") return
        ajax.compareCurrenies(from, to)
            .then((response) => {
                setMid(response.data.to[0].mid)
            })
            .catch((error) => {
                console.log("error")
            })
    }, [from, to])

    const setSelect = (select, isFrom) => {
        var value = select.target.value
        if (isFrom) {
            setFrom(value)
            if (to == "") {
                setTo(value)
            }
        } else if (value != ""){
            setTo(value)
        } else {
            setTo(from)
        }
    }

    return (
        <>
            <form>
                <Flex h="20%" my={6} flexDir="column" justifyContent={"center"}>

                    <FormControl mb={6}>
                        <FormLabel>
                            Choose a Currencies !
                        </FormLabel>
                        <Select placeholder='Select Currency from' onChange={(select) => setSelect(select, true)}>
                            {
                                currencies.length == 0 && GetAllCurrencies() ||
                                currencies
                            }
                        </Select>
                        <Select placeholder='Select Currency to' onChange={(select) => setSelect(select, false)}>
                            {
                                currencies.length == 0 && GetAllCurrencies() ||
                                currencies
                            }
                        </Select>
                    </FormControl>
                </Flex>
                {
                    from != "" &&
                    <SmallCurrency from={from} to={to} mid={mid} />
                }
            </form>
        </>
    )
}