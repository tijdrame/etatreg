export interface IResetPassord {
  login?: string;
  newPassword?: string;
}

export class ResetPassord implements IResetPassord {
  constructor(public login?: string, public newPassword?: string) {}
}
