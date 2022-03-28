import React from 'react'

const Image = (props) => {
  console.log(props.jpgName)
  return (
    <img src={require(`${props.jpgName}`)} width="500" height="600"></img>
  )
}

export default Image