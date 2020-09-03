import { Component, OnInit } from '@angular/core';
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';

import { User } from 'app/core/user/user.model';
import { UserService } from 'app/core/user/user.service';
import { isUndefined } from 'util';
import { ResetPassord } from 'app/shared/model/reset-password';
import { JhiAlertService } from 'ng-jhipster';

@Component({
  selector: 'jhi-user-mgmt-update',
  templateUrl: './user-management-reset.component.html',
})
export class UserManagementResetComponent implements OnInit {
  user!: User;
  login = '';
  newPassword = '';
  authorities: string[] = [];
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    login: [
      '',
      [
        Validators.required,
        Validators.minLength(1),
        Validators.maxLength(50),
        Validators.pattern('^[a-zA-Z0-9!$&*+=?^_`{|}~.-]+@[a-zA-Z0-9-]+(?:\\.[a-zA-Z0-9-]+)*$|^[_.@A-Za-z0-9-]+$'),
      ],
    ],
    /* firstName: ['', [Validators.maxLength(50)]],
    lastName: ['', [Validators.maxLength(50)]],
    email: ['', [Validators.minLength(5), Validators.maxLength(254), Validators.email]],
    activated: [],
    langKey: [],
    authorities: [], */
  });

  constructor(
    private userService: UserService,
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private jhiAlertService: JhiAlertService
  ) {}

  ngOnInit(): void {
    /* this.route.data.subscribe(({ user }) => {
      if (user) {
        this.user = user;
        if (this.user.id === undefined) {
          this.user.activated = true;
        }
        this.updateForm(user);
      }
    });
    alert(JSON.stringify(this.user)+' user')
    this.userService.authorities().subscribe(authorities => {
      this.authorities = authorities;
    }); */
    this.route.data.subscribe(({ user }) => (this.user = user));
    // alert(JSON.stringify(this.user)+' user')
    if (!isUndefined(this.user)) this.login = this.user.login!;
    // alert('login='+this.login)
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    // alert('saving'+this.newPassword+' login=')
    const iReset = new ResetPassord(this.login, this.newPassword);
    this.userService.reset(iReset).subscribe(
      () => {
        this.jhiAlertService.info('Mot de passe réinitialiser avec succés.');
        this.onSaveSuccess();
        // window.history.back();
      },
      () => this.jhiAlertService.error('Une erreur est survenue, la taille du mot de passe doit être supérieur à 3.')
    );
  }

  private updateForm(user: User): void {
    this.editForm.patchValue({
      id: user.id,
      login: user.login,
      /* firstName: user.firstName,
      lastName: user.lastName,
      email: user.email,
      activated: user.activated,
      langKey: user.langKey,
      authorities: user.authorities, */
    });
  }

  private updateUser(user: User): void {
    user.login = this.editForm.get(['login'])!.value;
    user.firstName = this.editForm.get(['firstName'])!.value;
    user.lastName = this.editForm.get(['lastName'])!.value;
    user.email = this.editForm.get(['email'])!.value;
    user.activated = this.editForm.get(['activated'])!.value;
    user.langKey = this.editForm.get(['langKey'])!.value;
    user.authorities = this.editForm.get(['authorities'])!.value;
  }

  private onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  private onSaveError(): void {
    this.isSaving = false;
  }
}
