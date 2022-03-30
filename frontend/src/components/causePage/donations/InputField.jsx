
const InputField = ({ name, placeholder, style }) => {
  // function handleChange(event) {
  //   console.log(event.currentTarget.value);
  //   setState(event.currentTarget.value);
  // }

  return (
    <input className={style} type="text" name={name} placeholder={placeholder} />
  );
};

export default InputField;
