import React from 'react'
import TextField from "@material-ui/core/TextField";
import {Grid} from "@material-ui/core";

export default function CustomTextField({type}) {
    let labeltype = ""
    if(type === "email"){
        labeltype = "이메일 주소";
    }
    else if (type === "password") {
        labeltype = "패스워드";
    } else {
        
    }
    return (
        <Grid item xs={12}>
            <TextField
                variant="outlined"
                required
                fullWidth
                id = {type}
                label = {labeltype}
                name = {type}
                autoComplete = {type}
            />
        </Grid>
        
    )
}
