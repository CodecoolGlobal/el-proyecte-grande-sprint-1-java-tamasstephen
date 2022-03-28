
const InputField = ({ setState, placeholder }) => {
  function handleChange(event) {
    console.log(event.currentTarget.value);
    setState(event.currentTarget.value);
  }

  return (
    <input type="text" onChange={handleChange} placeholder={placeholder} />
  );
};

export default InputField;
