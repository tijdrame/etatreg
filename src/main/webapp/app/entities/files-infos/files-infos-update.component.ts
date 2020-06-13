import { Component, OnInit } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
// eslint-disable-next-line @typescript-eslint/no-unused-vars
import { FormBuilder, Validators } from '@angular/forms';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IFilesInfos, FilesInfos } from 'app/shared/model/files-infos.model';
import { FilesInfosService } from './files-infos.service';

@Component({
  selector: 'jhi-files-infos-update',
  templateUrl: './files-infos-update.component.html',
})
export class FilesInfosUpdateComponent implements OnInit {
  isSaving = false;

  editForm = this.fb.group({
    id: [],
    codeFile: [null, [Validators.required]],
    description: [],
    inputPath: [],
    outputPath: [],
    dateDernierChargement: [],
    dateDernierDechargement: [],
  });

  constructor(protected filesInfosService: FilesInfosService, protected activatedRoute: ActivatedRoute, private fb: FormBuilder) {}

  ngOnInit(): void {
    this.activatedRoute.data.subscribe(({ filesInfos }) => {
      if (!filesInfos.id) {
        const today = moment().startOf('day');
        filesInfos.dateDernierChargement = today;
        filesInfos.dateDernierDechargement = today;
      }

      this.updateForm(filesInfos);
    });
  }

  updateForm(filesInfos: IFilesInfos): void {
    this.editForm.patchValue({
      id: filesInfos.id,
      codeFile: filesInfos.codeFile,
      description: filesInfos.description,
      inputPath: filesInfos.inputPath,
      outputPath: filesInfos.outputPath,
      dateDernierChargement: filesInfos.dateDernierChargement ? filesInfos.dateDernierChargement.format(DATE_TIME_FORMAT) : null,
      dateDernierDechargement: filesInfos.dateDernierDechargement ? filesInfos.dateDernierDechargement.format(DATE_TIME_FORMAT) : null,
    });
  }

  previousState(): void {
    window.history.back();
  }

  save(): void {
    this.isSaving = true;
    const filesInfos = this.createFromForm();
    if (filesInfos.id !== undefined) {
      this.subscribeToSaveResponse(this.filesInfosService.update(filesInfos));
    } else {
      this.subscribeToSaveResponse(this.filesInfosService.create(filesInfos));
    }
  }

  private createFromForm(): IFilesInfos {
    return {
      ...new FilesInfos(),
      id: this.editForm.get(['id'])!.value,
      codeFile: this.editForm.get(['codeFile'])!.value,
      description: this.editForm.get(['description'])!.value,
      inputPath: this.editForm.get(['inputPath'])!.value,
      outputPath: this.editForm.get(['outputPath'])!.value,
      dateDernierChargement: this.editForm.get(['dateDernierChargement'])!.value
        ? moment(this.editForm.get(['dateDernierChargement'])!.value, DATE_TIME_FORMAT)
        : undefined,
      dateDernierDechargement: this.editForm.get(['dateDernierDechargement'])!.value
        ? moment(this.editForm.get(['dateDernierDechargement'])!.value, DATE_TIME_FORMAT)
        : undefined,
    };
  }

  protected subscribeToSaveResponse(result: Observable<HttpResponse<IFilesInfos>>): void {
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
