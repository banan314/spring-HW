export class Token {
  private _token: string;

  get token(): string {
    return this._token;
  }

  set token(value: string) {
    this._token = value;
  }
}
