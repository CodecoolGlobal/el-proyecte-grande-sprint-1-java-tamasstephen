import React from 'react'

const Image = (props) => {
  return (
    <img src={require(`${props.jpgName}`)} alt='' width="500" height="600"></img>
  )
}

export default Image