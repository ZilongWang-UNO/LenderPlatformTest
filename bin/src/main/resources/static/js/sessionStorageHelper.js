const getSessionVar = (key) => {
  return window.sessionStorage.getItem(key);
}

const setSessionVar = (key, value) => {
  if(value === undefined || value === null) {
    window.sessionStorage.setItem(key, "");
  }
  window.sessionStorage.setItem(key, value);
}

const destroySession = () => {
  window.sessionStorage.clear();
}

export { getSessionVar, setSessionVar, destroySession };
