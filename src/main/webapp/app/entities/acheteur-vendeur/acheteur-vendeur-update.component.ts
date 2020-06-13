import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IAcheteurVendeur, AcheteurVendeur } from 'app/shared/model/acheteur-vendeur.model';
import { AcheteurVendeurService } from './acheteur-vendeur.service';

@Component({
  selector: 'jhi-acheteur-vendeur-update',
  templateUrl: './acheteur-vendeur-update.component.html',
})
export class AcheteurVendeurUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeAcheteurVendeur: [null, [Validators.required]],
    description: [],
    codeInterne: [],
  });

  constructor(
    protected acheteurVendeurService: AcheteurVendeurService,
    protected activatedRoute: ActivatedRoute,
    private fb: FormBuilder
  ) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ acheteurVendeur }) => {
      this.updateForm(acheteurVendeur);
    });
  }

  updateForm(acheteurVendeur: IAcheteurVendeur): void {
    this.editForm.patchValue({
      id: acheteurVendeur.id,
      codeAcheteurVendeur: acheteurVendeur.codeAcheteurVendeur,
      description: acheteurVendeur.description,
      codeInterne: acheteurVendeur.codeInterne,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const acheteurVendeur = this.createFromForm();
    if (acheteurVendeur.id !== undefined) {
      this.subscribeToSaveResponse(this.acheteurVendeurService.update(acheteurVendeur));
    } else {
      this.subscribeToSaveResponse(this.acheteurVendeurService.create(acheteurVendeur));
    }
  }

  private createFromForm(): IAcheteurVendeur {
    return {
      ...new AcheteurVendeur(),
      id: this.editForm.get(['id'])!.value,
      codeAcheteurVendeur: this.editForm.get(['codeAcheteurVendeur'])!.value,
      description: this.editForm.get(['description'])!.value,
      codeInterne: this.editForm.get(['codeInterne'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IAcheteurVendeur>>): void {
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
