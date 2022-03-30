export const dataHandler = {
  async getHighlights() {
    return this.getApi("highlighted");
  },

  async getImageForCard(causeLink) {
    return await this.getImage(`creator/${causeLink}/image`);
  },

  async registerUser(payload) {
    return await this.postTextJson("user", payload);
  },

  async logout() {
    return await this.getApi("logout");
  },

  async getAllCauses() {
    return await this.getApi("all-creators");
  },

  async getCausesByName(name) {
    return await this.getApi(`creators?name=${name}`);
  },

  async login(payload) {
    return await this.postTextJson("login", payload);
  },

  async getProfile() {
    return await this.getApi("user-profile");
  },

  async isCreatorProfileSet() {
    return await this.getApi("creator-profile-set");
  },

  async updateEmail(payLoad) {
    return await this.putApi("user/email", payLoad);
  },

  async getApi(endpoint) {
    const result = await fetch(`http://localhost:8080/${endpoint}`);
    return await result.json();
  },

  async postMultiPartForm(formData) {
    const result = await fetch("http://localhost:8080/creator-profile", {
      method: "POST",
      body: formData,
    });
    return await result.json();
  },

  async postTextJson(endpoint, payload) {
    const result = await fetch(`http://localhost:8080/${endpoint}`, {
      headers: { "Content-Type": "application/json" },
      method: "POST",
      body: JSON.stringify(payload),
    });
    return await result.json();
  },

  async putApi(endpoint, payLoad) {
    const result = await fetch(`http://localhost:8080/${endpoint}`, {
      headers: { "Content-Type": "application/json" },
      method: "PUT",
      body: JSON.stringify(payLoad),
    });
    return await result.json();
  },

  async getImage(endpoint) {
    const result = await fetch(`http://localhost:8080/${endpoint}`);
    return await result.blob();
  },
};
