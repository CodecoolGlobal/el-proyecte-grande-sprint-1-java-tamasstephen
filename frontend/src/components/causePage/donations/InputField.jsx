
const InputField = ({ name, placeholder, style, valueSetter, inputValue }) => {
  // function handleChange(event) {
  //   console.log(event.currentTarget.value);
  //   setState(event.currentTarget.value);
  // }

  function handleChange(event) {
    valueSetter(event.target.value);
  }

  return (
    <input
      className={style}
      type="text"
      name={name}
      placeholder={placeholder}
      onChange={handleChange}
      value={inputValue}
    />
  );
};

export default InputField;
