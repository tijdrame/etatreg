import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBp4Infos, Bp4Infos } from 'app/shared/model/bp-4-infos.model';
import { Bp4InfosService } from './bp-4-infos.service';

@Component({
  selector: 'jhi-bp-4-infos-update',
  templateUrl: './bp-4-infos-update.component.html',
})
export class Bp4InfosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeIsoPays: [],
    libellePays: [],
    mntnosCartes: [],
    mntCartesEtr: [],
  });

  constructor(protected bp4InfosService: Bp4InfosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp4Infos }) => {
      this.updateForm(bp4Infos);
    });
  }

  updateForm(bp4Infos: IBp4Infos): void {
    this.editForm.patchValue({
      id: bp4Infos.id,
      codeIsoPays: bp4Infos.codeIsoPays,
      libellePays: bp4Infos.libellePays,
      mntnosCartes: bp4Infos.mntnosCartes,
      mntCartesEtr: bp4Infos.mntCartesEtr,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp4Infos = this.createFromForm();
    if (bp4Infos.id !== undefined) {
      this.subscribeToSaveResponse(this.bp4InfosService.update(bp4Infos));
    } else {
      this.subscribeToSaveResponse(this.bp4InfosService.create(bp4Infos));
    }
  }

  private createFromForm(): IBp4Infos {
    return {
      ...new Bp4Infos(),
      id: this.editForm.get(['id'])!.value,
      codeIsoPays: this.editForm.get(['codeIsoPays'])!.value,
      libellePays: this.editForm.get(['libellePays'])!.value,
      mntnosCartes: this.editForm.get(['mntnosCartes'])!.value,
      mntCartesEtr: this.editForm.get(['mntCartesEtr'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp4Infos>>): void {
    result.subscribe(
      () => this.onSaveSuccess(),
      () => this.onSaveError()
    );
  }

  protected onSaveSuccess(): void {
    this.isSaving = false;
    this.previousState();
  }

  protected onSaveError(): void {
    this.isSaving = false;
  }
}
