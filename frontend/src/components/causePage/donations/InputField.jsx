
const InputField = ({ name, placeholder }) => {
  // function handleChange(event) {
  //   console.log(event.currentTarget.value);
  //   setState(event.currentTarget.value);
  // }

  return (
    <input type="text" name={name} placeholder={placeholder} />
  );
};

export default InputField;
