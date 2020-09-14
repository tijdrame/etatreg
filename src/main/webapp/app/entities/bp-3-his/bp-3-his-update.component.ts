import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';

import { IBp3His, Bp3His } from 'app/shared/model/bp-3-his.model';
import { Bp3HisService } from './bp-3-his.service';

@Component({
  selector: 'jhi-bp-3-his-update',
  templateUrl: './bp-3-his-update.component.html',
})
export class Bp3HisUpdateComponent implements OnInit {
  isSaving = false;
  dcoDp: any;
  dateArreteDp: any;

  editForm = this.fb.group({
    id: [],
    dco: [],
    age: [],
    dev: [],
    ncp: [],
    ope: [],
    lib: [],
    mon: [],
    sen: [],
    pie: [],
    ncc: [],
    uti: [],
    utf: [],
    nat: [],
    nomFic: [],
    dateArrete: [],
  });

  constructor(protected bp3HisService: Bp3HisService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ bp3His }) => {
      this.updateForm(bp3His);
    });
  }

  updateForm(bp3His: IBp3His): void {
    this.editForm.patchValue({
      id: bp3His.id,
      dco: bp3His.dco,
      age: bp3His.age,
      dev: bp3His.dev,
      ncp: bp3His.ncp,
      ope: bp3His.ope,
      lib: bp3His.lib,
      mon: bp3His.mon,
      sen: bp3His.sen,
      pie: bp3His.pie,
      ncc: bp3His.ncc,
      uti: bp3His.uti,
      utf: bp3His.utf,
      nat: bp3His.nat,
      nomFic: bp3His.nomFic,
      dateArrete: bp3His.dateArrete,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const bp3His = this.createFromForm();
    if (bp3His.id !== undefined) {
      this.subscribeToSaveResponse(this.bp3HisService.update(bp3His));
    } else {
      this.subscribeToSaveResponse(this.bp3HisService.create(bp3His));
    }
  }

  private createFromForm(): IBp3His {
    return {
      ...new Bp3His(),
      id: this.editForm.get(['id'])!.value,
      dco: this.editForm.get(['dco'])!.value,
      age: this.editForm.get(['age'])!.value,
      dev: this.editForm.get(['dev'])!.value,
      ncp: this.editForm.get(['ncp'])!.value,
      ope: this.editForm.get(['ope'])!.value,
      lib: this.editForm.get(['lib'])!.value,
      mon: this.editForm.get(['mon'])!.value,
      sen: this.editForm.get(['sen'])!.value,
      pie: this.editForm.get(['pie'])!.value,
      ncc: this.editForm.get(['ncc'])!.value,
      uti: this.editForm.get(['uti'])!.value,
      utf: this.editForm.get(['utf'])!.value,
      nat: this.editForm.get(['nat'])!.value,
      nomFic: this.editForm.get(['nomFic'])!.value,
      dateArrete: this.editForm.get(['dateArrete'])!.value,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IBp3His>>): void {
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
